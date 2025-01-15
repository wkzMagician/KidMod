package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.DisguisePower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

public class Disguise extends KidCard {
	public static final String ID = makeID(Disguise.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.POWER,
			CardRarity.RARE,
			CardTarget.SELF,
			1
	);

	public Disguise() {
		super(ID, info);

		setMagic(1, 1);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new DisguisePower(p, this.magicNumber)));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Disguise();
	}
}