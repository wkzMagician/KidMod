package Kid.cards.common;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PokerFace extends KidCard {
	public static final String ID = makeID(PokerFace.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			0
	);

	private static final int BLOCK = 10;
	private static final int UPG_BLOCK = 4;

	public PokerFace() {
		super(ID, info);

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractCard c : p.hand.group) {
			if(c == this) continue;
			if (c instanceof KidCard && !((KidCard) c).isReverse()) {
				return;
			}
		}
		addToBot(new GainBlockAction(p, p, block));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new PokerFace();
	}
}