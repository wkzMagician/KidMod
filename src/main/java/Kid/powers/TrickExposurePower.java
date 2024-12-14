package Kid.powers;
import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class TrickExposurePower extends BasePower {
		public static final String POWER_ID = "Kid:TrickExposurePower";
		public static final String NAME = "Trick Exposure";
		public static final String[] DESCRIPTIONS = new String[] {
						"Whenever you play a reversed Kid card, flip it to front."
		};

		public TrickExposurePower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("TrickExposure");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
				this.description = DESCRIPTIONS[0];
		}

		@Override
		public void onUseCard(AbstractCard card, UseCardAction action) {
			if (card instanceof KidCard && ((KidCard) card).isReverse()) {
				((KidCard) card).setFlipped(false);
			}
			flash();
		}
}
