package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Skywalk extends KidCard {
	public static final String ID = makeID(Skywalk.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			2
	);

	private static final int BLOCK = 10;
	private static final int UPG_BLOCK = 2;

	// 预见
	private static final int PLATED_ARMOR = 2;
	private static final int UPG_PLATED_ARMOR = 1;

	public Skywalk() {
		super(ID, info);

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
		setMagic(PLATED_ARMOR, UPG_PLATED_ARMOR); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, this.block));
		if(this.isReverse()){
			addToBot(new ApplyPowerAction(p, p, new com.megacrit.cardcrawl.powers.PlatedArmorPower(p, this.magicNumber), this.magicNumber));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Skywalk();
	}
}