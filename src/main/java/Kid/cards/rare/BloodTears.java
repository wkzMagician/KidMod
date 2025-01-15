package Kid.cards.rare;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.powers.BloodTearsPower;
import Kid.powers.PandoraPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodTears extends GemCard {
	public static final String ID = makeID(BloodTears.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			-2
	);

	public BloodTears() {
		super(ID, info);

		setMagic(1, 1);
	}

	@Override
	public void addPower() {
		super.addPower();

		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new BloodTearsPower(AbstractDungeon.player, magicNumber),
				magicNumber
		));
	}

	@Override
	public void removePower() {
		super.removePower();

		// 获得能力
		AbstractPower power = AbstractDungeon.player.getPower(BloodTearsPower.POWER_ID);
		if(power == null) return;

		int amount = power.amount;

		if(amount > magicNumber){
			addToBot(new ApplyPowerAction(
					AbstractDungeon.player,
					AbstractDungeon.player,
					new BloodTearsPower(AbstractDungeon.player, -magicNumber),
					-magicNumber
			));
		}else{
			addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, BloodTearsPower.POWER_ID));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new BloodTears();
	}
}