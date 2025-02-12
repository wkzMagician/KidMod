package Kid.cards.uncommon;

import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.BlueBirthdayPower;
import Kid.powers.DarkStarPower;
import Kid.powers.ElfLipsPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ElfLips extends GemCard {
	public static final String ID = makeID(ElfLips.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			-2
	);

	private static final int DISPLAY_AMOUNT = 3;
	private static final int UPG_DISPLAY_AMOUNT = 2;

	public ElfLips() {
		super(ID, info);
		setMagic(DISPLAY_AMOUNT, UPG_DISPLAY_AMOUNT);
	}

	@Override
	public void triggerOnOtherCardPlayed(AbstractCard c) {
		super.triggerOnOtherCardPlayed(c);

//		if(c instanceof KidCard && !((KidCard) c).isReverse()) {
//			addToBot(new DiscardSpecificCardAction(this));
//		}
	}

	@Override
	public void addPower() {
		super.addPower();

		AbstractPower power = AbstractDungeon.player.getPower(ElfLipsPower.POWER_ID);
		if(power != null) return;

		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new ElfLipsPower(AbstractDungeon.player, magicNumber)
		));
	}

	@Override
	public void removePower() {
		super.removePower();

		AbstractPower power = AbstractDungeon.player.getPower(ElfLipsPower.POWER_ID);
		if(power == null) return;

		int amount = power.amount;

		addToBot(new ReducePowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				ElfLipsPower.POWER_ID,
				magicNumber
		));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new ElfLips();
	}
}