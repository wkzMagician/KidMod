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

		if(this.upgraded) return;

		if(c instanceof KidCard && !((KidCard) c).isReverse()) {
			addToBot(new DiscardSpecificCardAction(this));
		}
	}

	private void setCardRarityVisible(boolean visible) {
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			if(c instanceof KidCard && ((KidCard) c).isReverse()){
				KidCard kc = (KidCard) c;
				kc.rarity = visible ? kc.actualRarity : CardRarity.SPECIAL;
			}
		}
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			if(c instanceof KidCard && ((KidCard) c).isReverse()){
				KidCard kc = (KidCard) c;
				kc.rarity = visible ? kc.actualRarity : CardRarity.SPECIAL;
			}
		}
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if(c instanceof KidCard && ((KidCard) c).isReverse()){
				KidCard kc = (KidCard) c;
				kc.rarity = visible ? kc.actualRarity : CardRarity.SPECIAL;
			}
		}
	}

	@Override
	public void addPower() {
		super.addPower();

		AbstractPower power = AbstractDungeon.player.getPower(ElfLipsPower.POWER_ID);
		if(power != null) return;

		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new ElfLipsPower(AbstractDungeon.player)
		));

		setCardRarityVisible(true);
	}

	@Override
	public void removePower() {
		super.removePower();

		// 遍历手牌中是否还存在其它ElfLips
		boolean toRemove = true;
		for(AbstractCard c : AbstractDungeon.player.hand.group){
			if(c instanceof ElfLips && c != this) {
				toRemove = false;
				break;
			}
		}

		if(toRemove){
			addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ElfLipsPower.POWER_ID));
		}

		setCardRarityVisible(false);

		applyPowers();
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new ElfLips();
	}
}