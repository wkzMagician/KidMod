package Kid.cards.rare;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SleepGas extends KidCard {
	public static final String ID = makeID(SleepGas.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			2
	);

	private static final int MAGIC = 2;
	private static final int UPG_MAGIC = 1;


	public SleepGas() {
		super(ID, info);

		setMagic(MAGIC, UPG_MAGIC); //Sets the card's magic number and how much it changes when upgraded.

		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 降低所有敌人的力量
		for(AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
			addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new SleepGas();
	}
}