package Kid.cards.common;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class FansSupport extends KidCard {
	public static final String ID = makeID(FansSupport.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			1
	);

	private static final int DAMAGE = 5;
	private static final int UPG_DAMAGE = 3;

	private static final int CHARM = 4;
	private static final int UPG_CHARM = -CHARM;

	public FansSupport() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
		setMagic(CHARM,
				UPG_CHARM); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));

		// 获得能力的魅力层数
		AbstractPower power = p.getPower("Kid:CharmPower");
		if(power == null) return;
		int charm = power.amount;

		if(charm > magicNumber) {
			addToBot(new GainEnergyAction(1));
			addToBot(new DrawCardAction(1));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new FansSupport();
	}
}