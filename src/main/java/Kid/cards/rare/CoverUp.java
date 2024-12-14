package Kid.cards.rare;

import Kid.cards.KidCard;
import Kid.cards.Status.FakeGem;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import hermit.powers.Drained;

public class CoverUp extends KidCard {
	public static final String ID = makeID(CoverUp.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			3
	);

	private static final int ENERGY_LOSS = 2;
	private static final int UPGRADE_ENERGY_LOSS = -1;

	public CoverUp() {
		super(ID, info);

		setMagic(ENERGY_LOSS, UPGRADE_ENERGY_LOSS);

		setCostUpgrade(2);

		this.cardsToPreview = new FakeGem();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractCard fakeGem = new FakeGem();
		addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1)));
		addToBot(new MakeTempCardInHandAction(fakeGem, 1));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new CoverUp();
	}
}