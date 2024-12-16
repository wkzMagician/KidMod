package Kid.cards.rare;

import Kid.actions.RemoveBuffsAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.CharmPower;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArmsCarry extends KidCard {
	public static final String ID = makeID(ArmsCarry.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.RARE,
			CardTarget.ENEMY,
			3
	);

	private static final int DAMAGE = 18;
	private static final int UPG_DAMAGE = 6;

	public ArmsCarry() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageCallbackAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, damage -> {
			// 获得造成伤害层数的魅力
			int charm = damage;
			if(charm <= 0) return;
			addToBot(new ApplyPowerAction(p, p, new CharmPower(p, charm), charm));
		}));
	}

	//

	@Override
	public AbstractCard makeCopy() { //Optional
		return new ArmsCarry();
	}
}