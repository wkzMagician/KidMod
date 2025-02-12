package Kid.cards.rare;

import Kid.actions.RemoveBuffsAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cleanse extends KidCard {
	public static final String ID = makeID(Cleanse.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.RARE,
			CardTarget.ALL_ENEMY,
			2
	);

	private static final int DAMAGE = 10;


	public Cleanse() {
		super(ID, info);

		setDamage(10);

		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 除了力量
		ArrayList<Integer> strengthPowerAmount = new ArrayList<Integer>();
		for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
			AbstractPower power = monster.getPower(StrengthPower.POWER_ID);
			int amount = 0;
			if(power != null){
				amount = power.amount;
			}
			strengthPowerAmount.add(amount);
		}

		// clean the buff of all enemys?
		if(!this.upgraded){
			// 遍历敌人
			for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
				// 敌人没有死亡或逃跑
				if(!monster.isDeadOrEscaped()){
					// 清除敌人的所有buff
					addToBot(new RemoveAllPowersAction(monster, false));
				}
			}
		}else{
			// 遍历敌人
			for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
				// 敌人没有死亡或逃跑
				if(!monster.isDeadOrEscaped()){
					// 清除敌人的所有buff
					addToBot(new RemoveBuffsAction(monster));
				}
			}
		}

		// 重新给予力量
		for(int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++){
			AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
			int amount = strengthPowerAmount.get(i);
			if(amount > 0 && !monster.isDeadOrEscaped()){
				addToBot(new ApplyPowerAction(monster, p, new StrengthPower(monster, amount), amount));
			}
		}

		// ALL ENEMY
		addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Cleanse();
	}
}