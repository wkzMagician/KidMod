package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.LoopholeDetectionPower;
import Kid.powers.MindMagicPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LoopholeDetection extends KidCard {
	public static final String ID = makeID(LoopholeDetection.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.POWER,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	public LoopholeDetection() {
		super(ID, info);

		this.isActual = true;
		setCostUpgrade(0);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new LoopholeDetectionPower(p, -1)));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new LoopholeDetection();
	}

}