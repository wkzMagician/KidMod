package Kid.actions;

import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;

public class UnflipCardInDrawPileAction extends AbstractGameAction {
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");

	public static final String[] TEXT = uiStrings.TEXT;

	private AbstractPlayer p;

//	public static int numFlipped = 0;

	private static final float DURATION = Settings.ACTION_DUR_XFAST;

	public UnflipCardInDrawPileAction(AbstractCreature target, AbstractCreature source, int amount) {
		this.p = (AbstractPlayer)target;
		setValues(target, source, amount);
		this.actionType = ActionType.SPECIAL;
		this.duration = DURATION;
		this.amount = amount;
	}

	public void update() {
		if (this.duration == DURATION) {
			if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
				this.isDone = true;
				return;
			}

			// 选择抽牌堆顶部的amount张牌
			ArrayList<AbstractCard> cards = new ArrayList<>();
			for (int i = 0; i < this.amount; i++) {
				AbstractCard c = this.p.drawPile.getNCardFromTop(i);
				cards.add(c);
			}

			// 翻到正面
			for (AbstractCard c : cards) {
				if (c instanceof KidCard) {
					((KidCard) c).setFlipped(false);
				}
			}

			this.isDone = true;

		}

		tickDuration();
	}
}
