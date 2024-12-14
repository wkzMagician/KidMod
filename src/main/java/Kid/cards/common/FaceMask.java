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

public class FaceMask extends KidCard {
	public static final String ID = makeID(FaceMask.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			1
	);

	private static final int BLOCK = 10;
	private static final int UPG_BLOCK = 3;

	public FaceMask() {
		super(ID, info);

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, block));
		addToBot(new SetCardSideAction(p, p, 3, Strategy.RANDOM, true));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new FaceMask();
	}
}