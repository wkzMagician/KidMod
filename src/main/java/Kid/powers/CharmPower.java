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

			// 是否有DisguisePower
			if(this.owner.hasPower("Kid:DisguisePower")) {
				// 获取DisguisePower
				DisguisePower power = (DisguisePower) this.owner.getPower("Kid:DisguisePower");
				// 如果DisguisePower的amount大于0
				if(power.amount > 0) {
					// 减少DisguisePower的amount
					power.amount--;
					// 更新DisguisePower的描述
					power.updateDescription();

					if(power.amount <= 0) {
						addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, power));
					}
				}
			}else{
				if(AbstractDungeon.player.hasRelic("Kid:Diamond")) {
					AbstractDungeon.player.getRelic("Kid:Diamond").flash();
					this.amount *= 0.75;
				}else{
					this.amount *= 0.5;
				}
				this.updateDescription();
			}

			if(this.amount <= 0) {
				addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
			}

			flash();
		}
}
