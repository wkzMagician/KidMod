package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Swap extends KidCard {
	public static final String ID = makeID(Swap.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:Swap");

	public Swap() {
		super(ID, info);

		setCostUpgrade(0);

		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 交换两张牌的能量面值
		addToBot(new SelectCardsInHandAction(2, cardStrings.EXTENDED_DESCRIPTION[0], false, false, c -> c.cost >= 0, list -> {
			AbstractCard c1 = list.get(0);
			AbstractCard c2 = list.get(1);

			int tmp = c1.costForTurn;
			c1.costForTurn = c2.costForTurn;
			c2.costForTurn = tmp;

			tmp = c1.cost;
			c1.cost = c2.cost;
			c2.cost = tmp;

			c1.superFlash();
			c2.superFlash();
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Swap();
	}
}