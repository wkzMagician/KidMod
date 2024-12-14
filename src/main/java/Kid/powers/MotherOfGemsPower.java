package Kid.powers;
import Kid.cards.KidCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class MotherOfGemsPower extends BasePower {
		public static final String POWER_ID = "Kid:MotherOfGemsPower";
		public static final String NAME = "Mother of Gems";
		public static final String[] DESCRIPTIONS = new String[] {
						"At the start of your turn, draw 1 additional card.",
				"At the start of your turn, draw #b2 additional cards."
		};

		public MotherOfGemsPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("MotherOfGems");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
				if(this.amount == 1) {
						this.description = DESCRIPTIONS[0];
				} else
			this.description = DESCRIPTIONS[1];
		}

		@Override
		public void atStartOfTurnPostDraw() {
			flash();
			addToBot(new DrawCardAction(this.owner, this.amount));
		}
}
