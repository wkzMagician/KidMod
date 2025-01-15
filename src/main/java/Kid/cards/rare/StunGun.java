package Kid.cards.rare;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StunGun extends KidCard {
	public static final String ID = makeID(StunGun.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.RARE,
			CardTarget.ENEMY,
			3
	);

	private static final int DAMAGE = 18;
	private static final int UPG_DAMAGE = 8;

	public StunGun() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE);

		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
		addToBot(new StunMonsterAction(m, p));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new StunGun();
	}
}