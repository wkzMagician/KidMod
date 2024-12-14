package Kid.powers;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BlueBirthdayPower extends BasePower {
		public static final String POWER_ID = "Kid:BlueBirthdayPower";
		public static final String NAME = "Blue Birthday";
		public static final String[] DESCRIPTIONS = new String[] {
						"Gain ",
				" additional energy at the start of your turn."
		};

		public BlueBirthdayPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("BlueBirthday");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
		}

		@Override
		public void atStartOfTurn() {
			flash();
			addToBot(new GainEnergyAction(this.amount));
		}
}
