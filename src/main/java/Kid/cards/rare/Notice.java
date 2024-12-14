package Kid.cards.rare;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Notice extends KidCard {
	public static final String ID = makeID(Notice.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			0
	);

	private static final int ENERGY = 1;
	private static final int UPGRADE_ENERGY = 1;

	private static final int DRAW = 2;


	public Notice() {
		super(ID, info);

		setMagic(ENERGY, UPGRADE_ENERGY);

		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DrawCardAction(p, DRAW));
		addToBot(new GainEnergyAction(magicNumber));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Notice();
	}
}