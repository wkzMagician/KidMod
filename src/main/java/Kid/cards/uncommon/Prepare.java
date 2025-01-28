package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Prepare extends KidCard {
	public static final String ID = makeID(Prepare.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final int BLOCK = 5;

	public Prepare() {
		super(ID, info);

		setBlock(BLOCK);

		this.isActual = true;

		this.cardsToPreview = (AbstractCard)new Miracle();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// shuffle a miracle into your draw pile
		Miracle miracle = new Miracle();
		if(upgraded) {
			miracle.upgrade();
		}
		addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)miracle, 1, true, true, false));
		addToBot(new GainBlockAction(p, p, this.block));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Prepare();
	}
}