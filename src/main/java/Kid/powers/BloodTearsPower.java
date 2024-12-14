package Kid.powers;
import Kid.cards.KidCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BloodTearsPower extends BasePower {
		public static final String POWER_ID = "Kid:BloodTearsPower";
		public static final String NAME = "Blood Tears";
		public static final String[] DESCRIPTIONS = new String[] {
						"At the end of your turn, exhaust ",
				" card ",
				" cards ",
				"At the end of your turn, you can choose ",
				"to exhaust"
		};

		private boolean upgraded = false;

		public BloodTearsPower(AbstractCreature owner, boolean upgraded, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("BloodTears");
				this.name = NAME;
				this.amount = amount;
				this.upgraded = upgraded;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
				if(!upgraded) {
					if(this.amount == 1) {
						this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
					} else {
						this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
					}
				} else {
					if(this.amount == 1) {
						this.description = DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[4];
					} else {
						this.description = DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[4];
					}
				}
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			if (isPlayer) {
				String desc = this.amount == -1 ? "Exhaust a card." : "Choose a card to exhaust.";
				if(!upgraded) {
					addToBot(new ExhaustAction(amount, false));
				} else{
					addToBot(new ExhaustAction(amount, true));
				}
			}
		}
}
