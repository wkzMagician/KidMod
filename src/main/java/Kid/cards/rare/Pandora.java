package Kid.cards.rare;

import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.MotherOfGemsPower;
import Kid.powers.PandoraPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Pandora extends GemCard {
	public static final String ID = makeID(Pandora.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			-2
	);

	private static final int HEAL_NUM = 3;
	private static final int UP_HEAL_NUM = 2;

	public Pandora() {
		super(ID, info);

		setMagic(HEAL_NUM, UP_HEAL_NUM);
	}

	@Override
	public void triggerWhenDrawn() {
		super.triggerWhenDrawn();

		if(otherGemInHand()) {
			addToBot(new DiscardSpecificCardAction(this));
		}
	}

	private boolean otherGemInHand() {
		for(AbstractCard c : AbstractDungeon.player.hand.group) {
			if (c instanceof GemCard && c != this) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addPower() {
		super.addPower();

		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new PandoraPower(AbstractDungeon.player, this.magicNumber),
				this.magicNumber
		));
	}

	@Override
	public void removePower() {
		super.removePower();

		AbstractPower power = AbstractDungeon.player.getPower(PandoraPower.POWER_ID);
		if(power == null) return;

		addToBot(new ReducePowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				PandoraPower.POWER_ID,
				magicNumber
		));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Pandora();
	}
}