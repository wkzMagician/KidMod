package Kid.powers;
import Kid.cards.KidCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BloodTearsPower extends BasePower {
		public static final String POWER_ID = "Kid:BloodTearsPower";

		public BloodTearsPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
		}

		@Override
		public void updateDescription() {
				if(this.amount == 1) {
					this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
				} else {
					this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
				}
		}

		@Override
		public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
			if (isPlayer) {
				addToBot(new ExhaustAction(amount, false, true, true));
			}
		}
}
