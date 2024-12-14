package Kid.cards.uncommon;

import Kid.actions.UnflipCardInDrawPileAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DeckInsight extends KidCard {
	public static final String ID = makeID(DeckInsight.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final int BLOCK = 5;
	private static final int UPG_BLOCK = 2;

	// 预见
	private static final int SCRY = 5;
	private static final int UPG_SCRY = 0;

	public DeckInsight() {
		super(ID, info);

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
		setMagic(SCRY, UPG_SCRY); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(this.upgraded) {
			addToBot(new UnflipCardInDrawPileAction(p, p, magicNumber));
		}
		// 预见Action
		addToBot(new ScryAction(magicNumber));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new DeckInsight();
	}
}