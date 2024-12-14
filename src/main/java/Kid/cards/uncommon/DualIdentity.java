package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class DualIdentity extends KidCard {
	public static final String ID = makeID(DualIdentity.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY,
			1
	);

	private static final int DAMAGE = 8;
	private static final int UPG_DAMAGE = 2;

	private static final int MAGIC = 1;
	private static final int UPG_MAGIC = 1;

	public DualIdentity() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE);
		setMagic(MAGIC, UPG_MAGIC);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(this.isReverse()){
			addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false)));
		}else{
			addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false)));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new DualIdentity();
	}

}