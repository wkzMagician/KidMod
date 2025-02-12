package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.HatTrickPower;
import Kid.powers.TrickExposurePower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HatTrick extends KidCard {
	public static final String ID = makeID(HatTrick.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.POWER,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final int MAGIC = 30;
//	private static final int UPG_MAGIC = 10;
	private static final int UPG_MAGIC = 10;

	public HatTrick() {
		super(ID, info);

		setMagic(MAGIC, UPG_MAGIC); //Sets the card's magic number and how much it changes when upgraded.

		setCostUpgrade(1);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new HatTrickPower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new HatTrick();
	}

}