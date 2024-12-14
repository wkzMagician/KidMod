package Kid.cards.basic;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends KidCard {
	public static final String ID = makeID(Defend.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.BASIC,
			CardTarget.SELF,
			1
	);

	private static final int BLOCK = 5;
	private static final int UPG_BLOCK = 3;

	public Defend() {
		super(ID, info);

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.

		tags.add(CardTags.STARTER_DEFEND);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, block));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Defend();
	}
}