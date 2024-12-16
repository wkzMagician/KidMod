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

		AbstractPower power = AbstractDungeon.player.getPower(ElfLipsPower.POWER_ID);
		if(power != null){
			int amount = power.amount;

			if(amount == -1 && upgraded){
				addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ElfLipsPower.POWER_ID));
				addToBot(new ApplyPowerAction(
						AbstractDungeon.player,
						AbstractDungeon.player,
						new ElfLipsPower(AbstractDungeon.player, -2)
				));
			}

			return;
		}

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

		AbstractPower power = AbstractDungeon.player.getPower(ElfLipsPower.POWER_ID);
		if(power == null) return;
		int amount = power.amount;

		if(amount == -2 && !upgraded) return;

		// 遍历手牌中是否还存在其它ElfLips
		int card_count = 0;
		for(AbstractCard c : AbstractDungeon.player.hand.group){
			if(c.cardID.equals(ElfLips.ID)){
				card_count++;
			}
		}

		if(card_count <= 1){
			addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ElfLipsPower.POWER_ID));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new ElfLips();
	}
}