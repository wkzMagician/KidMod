package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AlertHint extends KidCard {
	public static final String ID = makeID(AlertHint.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			0
	);

	private static final int BLOCK = 3;
	private static final int UPG_BLOCK = 1;

	private static final int FLIP_AMOUNT = 1;
	private static final int UPG_FLIP_AMOUNT = 1;

	public AlertHint() {
		super(ID, info);

		this.isActual = true;

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
		setMagic(FLIP_AMOUNT, UPG_FLIP_AMOUNT); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, block));
		addToBot(new SetCardSideAction(p, p, magicNumber, Strategy.SELECT, false));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new AlertHint();
	}
}