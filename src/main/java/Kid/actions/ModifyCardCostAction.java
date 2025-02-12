package Kid.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

public class ModifyCardCostAction extends AbstractGameAction {
	AbstractCard card;
	int amount;

	public ModifyCardCostAction(AbstractCard card, int amount) {
		this.card = card;
		this.amount = amount;
		this.duration = Settings.ACTION_DUR_FAST;
	}

	@Override
	public void update() {
		card.modifyCostForCombat(amount);
		this.isDone = true;
	}
}
