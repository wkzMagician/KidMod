package Kid.cards.rare;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.powers.MotherOfGemsPower;
import Kid.powers.PandoraPower;
import Kid.powers.ParisSunshinePower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ParisSunshine extends GemCard {
	public static final String ID = makeID(ParisSunshine.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			-2
	);

	private static final int CHARM = 2;
	private static final int UPG_CHARM = 1;

	public ParisSunshine() {
		super(ID, info);

		setMagic(CHARM, UPG_CHARM);
	}

	@Override
	public void removePower() {
		super.removePower();

		AbstractPower power = AbstractDungeon.player.getPower(ParisSunshinePower.POWER_ID);
		if(power == null) return;

		int amount = power.amount;

		if(amount > 1) {
			addToBot(new ApplyPowerAction(
					AbstractDungeon.player,
					AbstractDungeon.player,
					new ParisSunshinePower(AbstractDungeon.player, -magicNumber),
					-magicNumber
			));
		}else{
			addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ParisSunshinePower.POWER_ID));
		}
	}

	@Override
	public void addPower() {
		super.addPower();

		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new ParisSunshinePower(AbstractDungeon.player, this.magicNumber),
				this.magicNumber
		));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new ParisSunshine();
	}
}