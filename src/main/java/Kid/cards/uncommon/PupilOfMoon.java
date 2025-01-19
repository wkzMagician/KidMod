package Kid.cards.uncommon;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.powers.DarkStarPower;
import Kid.powers.PandoraPower;
import Kid.powers.ParisSunshinePower;
import Kid.powers.PupilOfMoonPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PupilOfMoon extends GemCard {
	public static final String ID = makeID(PupilOfMoon.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			-2
	);

	private static final int MAGIC_NUM = 2;
	private static final int UP_MAGIC_NUM = 1;

	public PupilOfMoon() {
		super(ID, info);

		setMagic(MAGIC_NUM, UP_MAGIC_NUM);
	}

	@Override
	public void addPower() {
		super.addPower();

		// 对玩家施加一个力量buff
		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new PupilOfMoonPower(AbstractDungeon.player, this.magicNumber),
				this.magicNumber
		));
	}

	@Override
	public void removePower() {
		super.removePower();

		AbstractPower power = AbstractDungeon.player.getPower(PupilOfMoonPower.POWER_ID);
		if(power == null) return;

		int amount = power.amount;

		addToBot(new ReducePowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				PupilOfMoonPower.POWER_ID,
				magicNumber
		));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new PupilOfMoon();
	}
}