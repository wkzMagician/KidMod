package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WhiteCloak extends KidCard {
	public static final String ID = makeID(WhiteCloak.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			1
	);

	private static final int BLOCK = 8;
	private static final int UPG_BLOCK = 2;

	private static final int FLIP = 2;
	private static final int UPG_FLIP = 3;

	public WhiteCloak() {
		super(ID, info);

		this.isActual = true;

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
		setMagic(FLIP, UPG_FLIP); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, this.block));
		addToBot(new FlipCardAction(p, p, this.magicNumber, Strategy.SELECT));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new WhiteCloak();
	}
}