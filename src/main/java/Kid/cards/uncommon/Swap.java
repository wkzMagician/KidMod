package Kid.cards.uncommon;

import Kid.actions.SetCardSideAction;
import Kid.actions.SwapCardEnergyAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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

	public Swap() {
		super(ID, info);

		setCostUpgrade(0);

		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 交换两张牌的能量面值
		addToBot(new SwapCardEnergyAction(p, p));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Swap();
	}
}