package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.cards.status.Fish;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flee extends KidCard {
	public static final String ID = makeID(Flee.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final int BLOCK = 12;
	private static final int UPG_BLOCK = 4;

	public Flee() {
		super(ID, info);

		setBlock(BLOCK, UPG_BLOCK);

		this.cardsToPreview = (AbstractCard)new Fish();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		Fish fish = new Fish();
		addToBot(new GainBlockAction(p, p, this.block));
		// Shuffle a Fish into your draw pile
		addToBot(new MakeTempCardInDrawPileAction(fish, 1, true, true, false));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Flee();
	}
}