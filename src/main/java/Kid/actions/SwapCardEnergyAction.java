package Kid.actions;

import Kid.cards.KidCard;
import Kid.cards.KidCard.Strategy;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;

public class SwapCardEnergyAction extends AbstractGameAction {
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Kid:SwapCardEnergyAction");

	public static final String[] TEXT = uiStrings.TEXT;

	private AbstractPlayer p;

	private static final float DURATION = Settings.ACTION_DUR_XFAST;

	public SwapCardEnergyAction(AbstractCreature target, AbstractCreature source) {
		this.p = (AbstractPlayer)target;
		this.actionType = ActionType.SPECIAL;
		this.duration = DURATION;
	}

	public void update() {
		if (this.p.hand.size() <= 1) {
			this.isDone = true;
			return;
		}

		if (this.duration == DURATION) {
			if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
				this.isDone = true;
				return;
			}

			// 选择手中的两张牌
			AbstractDungeon.handCardSelectScreen.open(TEXT[0], 2, false, false);
			tickDuration();
			return;
		}

		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			// 现在选择的两张牌
			// 交换它们的能量
			// 浅拷贝
			ArrayList<AbstractCard> cards = new ArrayList<>(AbstractDungeon.handCardSelectScreen.selectedCards.group);
			AbstractCard card1 = cards.get(0);
			AbstractCard card2 = cards.get(1);

			int diff = card1.cost - card2.cost;
			int diffForTurn = card1.costForTurn - card2.costForTurn;

			card1.cost -= diff;
			card2.cost += diff;
			card1.costForTurn -= diffForTurn;
			card2.costForTurn += diffForTurn;

			// 归还选择的牌
			for (AbstractCard c : cards) {
				this.p.hand.addToTop(c);
			}

			// 重置选择屏幕
			this.p.hand.applyPowers();
			AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}
