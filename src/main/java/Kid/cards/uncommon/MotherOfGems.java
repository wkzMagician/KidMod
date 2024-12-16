package Kid.cards.uncommon;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.powers.DarkStarPower;
import Kid.powers.MotherOfGemsPower;
import Kid.powers.PupilOfMoonPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawPower;

public class MotherOfGems extends GemCard {
	public static final String ID = makeID(MotherOfGems.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			-2
	);

	private static final int DRAW_NUM = 1;
	private static final int UP_DRAW_NUM = 1;

	public MotherOfGems() {
		super(ID, info);

		setMagic(DRAW_NUM, UP_DRAW_NUM);
	}

	@Override
	public void didDiscard() {
		// 弃置这张手牌
		addToBot(new DiscardSpecificCardAction(this));
	}

	@Override
	public void addPower() {
		super.addPower();

		// 对玩家施加一个力量buff
		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new MotherOfGemsPower(AbstractDungeon.player, this.magicNumber),
				this.magicNumber
		));
	}

	@Override
	public void removePower() {
		super.removePower();

		AbstractPower power = AbstractDungeon.player.getPower(MotherOfGemsPower.POWER_ID);
		if(power == null) return;

		int amount = power.amount;

		if(amount > 1) {
			addToBot(new ApplyPowerAction(
					AbstractDungeon.player,
					AbstractDungeon.player,
					new MotherOfGemsPower(AbstractDungeon.player, -magicNumber),
					-magicNumber
			));
		}else{
			addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, MotherOfGemsPower.POWER_ID));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new MotherOfGems();
	}
}