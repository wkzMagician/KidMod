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

public class SetCardSideRandomAction extends AbstractGameAction {
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Kid:FlipCardAction");

	public static final String[] TEXT = uiStrings.TEXT;

	private AbstractPlayer p;

	public static int numFlipped = 0;

	private static final float DURATION = Settings.ACTION_DUR_XFAST;

	private Strategy strategy;

	public SetCardSideRandomAction(AbstractCreature target, AbstractCreature source, int amount, Strategy strategy) {
		this.p = (AbstractPlayer)target;
		setValues(target, source, amount);
		this.actionType = ActionType.SPECIAL;
		this.duration = DURATION;
		this.strategy = strategy;
	}

	public void update() {
		if (this.duration == DURATION) {
			if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
				this.isDone = true;
				return;
			}

			if (this.p.hand.size() <= this.amount) {
				this.amount = this.p.hand.size();

				// 顺序遍历手中每一张牌
				for (int i = 0; i < this.p.hand.size(); i++) {

					AbstractCard c = this.p.hand.getNCardFromTop(i);

					// 判断:不是KidCard,则不执行
					if (!(c instanceof KidCard)) {
						continue;
					}

					// 随机翻转
					((KidCard) c).setFlipped(AbstractDungeon.cardRandomRng.randomBoolean());

				}
				AbstractDungeon.player.hand.applyPowers();
				tickDuration();
				return;
			}

			if (this.strategy == Strategy.RANDOM) {
				// 获取cardList
				ArrayList<AbstractCard> cardList = new ArrayList<>(this.p.hand.group);
				for (int i = 0; i < this.amount; i++) {
					// 随机获取一张牌
					AbstractCard c = cardList.get(AbstractDungeon.cardRandomRng.random(cardList.size() - 1));
					// 判断:不是KidCard,则不执行
					if (!(c instanceof KidCard)) {
						// 从cardList中移除这张牌
						cardList.remove(c);
						continue;
					}
					// 随机翻转
					((KidCard) c).setFlipped(AbstractDungeon.cardRandomRng.randomBoolean());

					cardList.remove(c);
				}
			} else if(this.strategy == Strategy.TOP) {
				for (int i = 0; i < this.amount; i++) {
					AbstractCard c = this.p.hand.getNCardFromTop(i);
					// 判断:不是KidCard,则不执行
					if (!(c instanceof KidCard)) {
						continue;
					}
					// 随机翻转
					((KidCard) c).setFlipped(AbstractDungeon.cardRandomRng.randomBoolean());
				}

			}else {
				if (this.amount < 0) {
					AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
					AbstractDungeon.player.hand.applyPowers();
					tickDuration();
					return;
				}

				numFlipped = this.amount;

				if (this.p.hand.size() > this.amount)
					AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
				AbstractDungeon.player.hand.applyPowers();
				tickDuration();
				return;

			}
		}

		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				// 判断:不是KidCard,则不执行
				if (!(c instanceof KidCard)) {
					this.p.hand.addToTop(c);
					continue;
				}
				// 随机翻转
				((KidCard) c).setFlipped(AbstractDungeon.cardRandomRng.randomBoolean());

				this.p.hand.addToTop(c);
			}

			this.p.hand.applyPowers();
			AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}
