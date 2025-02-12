package Kid.cards.rare;

import Kid.cards.KidCard;
import Kid.cards.status.FakeGem;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class CoverUp extends KidCard {
	public static final String ID = makeID(CoverUp.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			2
	);

	public CoverUp() {
		super(ID, info);

		setEthereal(true, false);

		this.cardsToPreview = new FakeGem();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractCard fakeGem = new FakeGem();
		addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1)));
		addToBot(new MakeTempCardInHandAction(fakeGem, 2));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new CoverUp();
	}
}