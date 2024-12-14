package Kid.actions;

import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;

public class FlipCardDrawAction extends AbstractGameAction {

	private AbstractPlayer p;

	private static final float DURATION = Settings.ACTION_DUR_XFAST;

	private int drawAmount;

	public FlipCardDrawAction(int amount) {
		this.p = AbstractDungeon.player;
		setValues((AbstractCreature)this.p, (AbstractCreature)this.p);
		this.actionType = ActionType.SPECIAL;
		this.duration = DURATION;
		this.drawAmount = amount;
	}

	public void update() {
		if (this.duration == DURATION) {
			if(p.hand.size() >= 10){
				this.isDone = true;
				return;
			}

			if (!p.drawPile.isEmpty()) {
				AbstractCard c = (AbstractCard) AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - 1);
				if(c instanceof KidCard && !((KidCard) c).isReverse()) {
					this.addToTop(new DrawCardAction(1));
				}else{
					this.addToTop(new FlipCardDrawAction(this.drawAmount - 1));
					this.addToTop(new DrawCardAction(1));
				}
			} else
			{
				this.addToTop(new FlipCardDrawAction(this.drawAmount));
				this.addToTop(new EmptyDeckShuffleAction());
			}

			this.isDone = true;
			return;

		}

		tickDuration();
	}
}
