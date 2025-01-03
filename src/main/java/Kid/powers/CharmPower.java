package Kid.powers;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CharmPower extends BasePower {
		public static final String POWER_ID = "Kid:CharmPower";

		public CharmPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount;
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
				flash();
				addToBot((new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE)));
			}
		}

		@Override
		public void wasHPLost(DamageInfo info, int damageAmount) {
			super.wasHPLost(info, damageAmount);

			this.amount /= 2;

			if(this.amount <= 0) {
				addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
			}

			flash();
		}
}
