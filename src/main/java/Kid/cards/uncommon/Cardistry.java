package Kid.cards.uncommon;

import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Cardistry extends KidCard {
	public static final String ID = makeID(Cardistry.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY,
			3
	);

	private static final int DAMAGE = 7;
	private static final int UPG_DAMAGE = 2;
	// 连击
	private static final int MULTI_DAMAGE = 3;

	public Cardistry() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0; i < MULTI_DAMAGE; i++){
			addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Cardistry();
	}

	@Override
	public void triggerOnFlip(){
		// 基类方法
		super.triggerOnFlip();

		// 每次翻转时，费用减1
		this.modifyCostForCombat(-1);
	}
}