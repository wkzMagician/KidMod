package Kid.cards.rare;

import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.PandoraPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Pandora extends GemCard {
	public static final String ID = makeID(Pandora.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			-2
	);

	private static final int HEAL_NUM = 2;
	private static final int UP_HEAL_NUM = 1;

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

		addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, PandoraPower.POWER_ID));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Pandora();
	}
}