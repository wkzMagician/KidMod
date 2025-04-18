package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.ScoutPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Scout extends KidCard {
	public static final String ID = makeID(Scout.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.POWER,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			2
	);

	private static final int MAGIC = 1;
	private static final int UPG_MAGIC = 0;

	public Scout() {
		super(ID, info);

		setMagic(MAGIC, UPG_MAGIC);
		setCostUpgrade(1);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new ScoutPower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Scout();
	}

}