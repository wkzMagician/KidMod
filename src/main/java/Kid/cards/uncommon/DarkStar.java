package Kid.cards.uncommon;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.powers.DarkStarPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DarkStar extends GemCard {
	public static final String ID = makeID(DarkStar.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			-2
	);

	private static final int MAGIC_NUM = 2;
	private static final int UP_MAGIC_NUM = 1;

	public DarkStar() {
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
				new DarkStarPower(AbstractDungeon.player, this.magicNumber),
				this.magicNumber
		));
	}

	@Override
	public void removePower() {
		super.removePower();

		addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, DarkStarPower.POWER_ID));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new DarkStar();
	}
}