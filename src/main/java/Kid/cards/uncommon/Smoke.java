package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Smoke extends KidCard {
	public static final String ID = makeID(Smoke.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	// 虚弱层数
	private static final int WEAK = 2;
	private static final int UPG_WEAK = 1;

	public Smoke() {
		super(ID, info);

		setMagic(WEAK, UPG_WEAK); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 目标：全体敌人
		for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
			addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Smoke();
	}
}