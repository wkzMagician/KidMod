package Kid.powers;
import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import java.util.Objects;

public class ReasonPower extends BasePower {
		public static final String POWER_ID = "Kid:ReasonPower";

		AbstractCard card = null;

		public ReasonPower(AbstractCreature owner, AbstractCard card, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.card = card;
				updateDescription();
		}

		@Override
		public void updateDescription() {
			// if the next card you play is ..., gain amount energy
			if(card == null) this.description = "uninitialized";
			else this.description = DESCRIPTIONS[0] + card.name + (this.amount == 1 ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
		}

	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card instanceof KidCard && ((KidCard) card).isReverse()) {
			if(Objects.equals(this.card.cardID, card.cardID)) {
				this.addToBot(new GainEnergyAction(this.amount));
			}

			// remove the power
			this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
		}
		flash();
	}

}
