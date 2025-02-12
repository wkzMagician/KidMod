package Kid.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class RepeatTrickAction extends AbstractGameAction {
	public RepeatTrickAction() {
	}

	public void update() {
		// 打出上一张打出的牌
		if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() < 2) {
			isDone = true;
			return;
		}

		AbstractCard originalCard = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2);
		AbstractCard cardCopy = originalCard.makeStatEquivalentCopy();

		cardCopy.energyOnUse = 0;
		AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(cardCopy, AbstractDungeon.getRandomMonster(), cardCopy.energyOnUse, true, true), true);

		// 在副本打出后将其移除
		AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
			@Override
			public void update() {
				if(cardCopy.type != CardType.POWER && !cardCopy.exhaust) {
					AbstractDungeon.player.discardPile.removeTopCard();
				}
				isDone = true;
			}
		});

		isDone = true;
	}
}
