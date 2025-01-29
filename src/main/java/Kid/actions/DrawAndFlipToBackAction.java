package Kid.actions;

import Kid.cards.KidCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DrawAndFlipToBackAction extends AbstractGameAction {
	private boolean shuffleCheck = false;

	private static final Logger logger = LogManager.getLogger(DrawAndFlipToBackAction.class.getName());

	public static ArrayList<AbstractCard> drawnCards = new ArrayList<>();

	private boolean clearDrawHistory = true;

	private AbstractGameAction followUpAction = null;

	public DrawAndFlipToBackAction(AbstractCreature source, int amount, boolean endTurnDraw) {
		if (endTurnDraw)
			AbstractDungeon.topLevelEffects.add(new PlayerTurnEffect());
		setValues((AbstractCreature)AbstractDungeon.player, source, amount);
		this.actionType = AbstractGameAction.ActionType.DRAW;
		if (Settings.FAST_MODE) {
			this.duration = Settings.ACTION_DUR_XFAST;
		} else {
			this.duration = Settings.ACTION_DUR_FASTER;
		}
	}

	public DrawAndFlipToBackAction(AbstractCreature source, int amount) {
		this(source, amount, false);
	}

	public DrawAndFlipToBackAction(int amount, boolean clearDrawHistory) {
		this(amount);
		this.clearDrawHistory = clearDrawHistory;
	}

	public DrawAndFlipToBackAction(int amount) {
		this((AbstractCreature)null, amount, false);
	}

	public DrawAndFlipToBackAction(int amount, AbstractGameAction action) {
		this(amount, action, true);
	}

	public DrawAndFlipToBackAction(int amount, AbstractGameAction action, boolean clearDrawHistory) {
		this(amount, clearDrawHistory);
		this.followUpAction = action;
	}

	public void update() {
		if (this.clearDrawHistory) {
			this.clearDrawHistory = false;
			drawnCards.clear();
		}
		if (AbstractDungeon.player.hasPower("No Draw")) {
			AbstractDungeon.player.getPower("No Draw").flash();
			endActionWithFollowUp();
			return;
		}
		if (this.amount <= 0) {
			endActionWithFollowUp();
			return;
		}
		int deckSize = AbstractDungeon.player.drawPile.size();
		int discardSize = AbstractDungeon.player.discardPile.size();
		if (SoulGroup.isActive())
			return;
		if (deckSize + discardSize == 0) {
			endActionWithFollowUp();
			return;
		}
		if (AbstractDungeon.player.hand.size() == 10) {
			AbstractDungeon.player.createHandIsFullDialog();
			endActionWithFollowUp();
			return;
		}
		if (!this.shuffleCheck) {
			if (this.amount + AbstractDungeon.player.hand.size() > 10) {
				this.amount = 10 - AbstractDungeon.player.hand.size();
				AbstractDungeon.player.createHandIsFullDialog();
			}
			if (this.amount > deckSize) {
				int tmp = this.amount - deckSize;
				addToTop(new DrawAndFlipToBackAction(tmp, this.followUpAction, false));
				addToTop(new EmptyDeckShuffleAction());
				if (deckSize != 0)
					addToTop(new DrawAndFlipToBackAction(deckSize, false));
				this.amount = 0;
				this.isDone = true;
				return;
			}
			this.shuffleCheck = true;
		}
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.amount != 0 && this.duration < 0.0F) {
			if (Settings.FAST_MODE) {
				this.duration = Settings.ACTION_DUR_XFAST;
			} else {
				this.duration = Settings.ACTION_DUR_FASTER;
			}
			this.amount--;
			if (!AbstractDungeon.player.drawPile.isEmpty()) {
				AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
				if(c instanceof KidCard) {
					((KidCard) c).setFlipped(true);
				}
				drawnCards.add(c);
				AbstractDungeon.player.draw();
				AbstractDungeon.player.hand.refreshHandLayout();
			} else {
				logger.warn("Player attempted to draw from an empty drawpile mid-DrawAction?MASTER DECK: " + AbstractDungeon.player.masterDeck

						.getCardNames());
				endActionWithFollowUp();
			}
			if (this.amount == 0)
				endActionWithFollowUp();
		}
	}

	private void endActionWithFollowUp() {
		this.isDone = true;
		if (this.followUpAction != null)
			addToTop(this.followUpAction);
	}
}
