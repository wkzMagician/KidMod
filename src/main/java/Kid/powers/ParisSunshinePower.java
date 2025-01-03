package Kid.powers;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class ParisSunshinePower extends BasePower {
		public static final String POWER_ID = "Kid:ParisSunshinePower";

		public ParisSunshinePower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
		}

		@Override
		public void updateDescription() {
				this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
		}

		@Override
		public void atStartOfTurn() {
				this.flash();
				this.addToBot(new ApplyPowerAction(this.owner, this.owner, new CharmPower(this.owner, this.amount), this.amount));
		}
}
