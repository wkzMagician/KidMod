package Kid.cards.uncommon;

import Kid.actions.FansSupportAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FansSupport extends KidCard {
	public static final String ID = makeID(FansSupport.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			-1
	);

	private static final int CHARM = 4;
	private static final int UPGRADE_CHARM = 2;

	public FansSupport() {
		super(ID, info);

		setMagic(CHARM, UPGRADE_CHARM);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new FansSupportAction(p, m, this.magicNumber, this.freeToPlayOnce, this.energyOnUse));

	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new FansSupport();
	}
}