package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RepeatTrick extends KidCard {
	public static final String ID = makeID(RepeatTrick.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	public RepeatTrick() {
		super(ID, info);

		setCostUpgrade(0);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// ! 将弃牌堆顶的牌打出
		if(p.discardPile.isEmpty()) return;
		AbstractCard dc = p.discardPile.getTopCard();
		if(dc == null) return;

		p.discardPile.moveToHand(dc);
		// 打出手牌堆顶的牌
//		addToBot(new UseCardAction(p.hand.getTopCard()));
		AbstractCard c = p.hand.getTopCard();
		c.energyOnUse = 0;
		AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, AbstractDungeon.getRandomMonster(), c.energyOnUse, true, true), true);

		// 将本张手牌能量+1
		modifyCostForCombat(1);
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new RepeatTrick();
	}
}