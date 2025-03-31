package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.RestitutionPower;
import Kid.powers.UnparalleledSkillPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Restitution extends KidCard {
	public static final String ID = makeID(Restitution.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.POWER,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final int MAGIC_NUM = 1;
	private static final int UP_MAGIC_NUM = 1;

	public Restitution() {
		super(ID, info);
		setMagic(MAGIC_NUM, UP_MAGIC_NUM);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new RestitutionPower(p, this.magicNumber)));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Restitution();
	}

}