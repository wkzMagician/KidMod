package Kid.cards.rare;

import Kid.actions.RemoveBuffsAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
		// ALL ENEMY
		addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		// clean the buff of all enemys?
		if(!this.upgraded){
			// 遍历敌人
			for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
				addToBot(new RemoveAllPowersAction(monster, false));
			}
		}else{
			// 遍历敌人
			for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
				addToBot(new RemoveBuffsAction(monster));
			}
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Cleanse();
	}
}