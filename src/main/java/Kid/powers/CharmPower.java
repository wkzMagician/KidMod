package Kid.powers;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CharmPower extends BasePower {
		public static final String POWER_ID = "Kid:CharmPower";
		public static final String NAME = "Charm";
		public static final String[] DESCRIPTIONS = new String[] {
						"At the end of your turn, reduce all enemies' HP by "
		};

		private AbstractCard markedCard = null;

		public CharmPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("charm");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
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
			// amount /= 2
			this.amount /= 2;

			super.wasHPLost(info, damageAmount);
		}
}
