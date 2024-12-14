package Kid.cards.uncommon;

import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.BlueBirthdayPower;
import Kid.powers.ElfLipsPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ElfLips extends GemCard {
	public static final String ID = makeID(ElfLips.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			-2
	);

	public ElfLips() {
		super(ID, info);
	}

	@Override
	public void triggerOnOtherCardPlayed(AbstractCard c) {
		super.triggerOnOtherCardPlayed(c);

		if(c instanceof KidCard && !((KidCard) c).isReverse()) {
			addToBot(new DiscardSpecificCardAction(this));
		}
	}

	@Override
	public void addPower() {
		super.addPower();

		int i = upgraded ? -2 : -1;
		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new ElfLipsPower(AbstractDungeon.player, i)
		));
	}

	@Override
	public void removePower() {
		super.removePower();

		addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ElfLipsPower.POWER_ID));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new ElfLips();
	}
}