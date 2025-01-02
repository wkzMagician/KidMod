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
		public static final String NAME = "Blood Tears";
		public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

		public BloodTearsPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("BloodTears");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
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
		public void atEndOfTurn(boolean isPlayer) {
			if (isPlayer) {
				addToBot(new ExhaustAction(amount, false, true, true));
			}
		}
}
