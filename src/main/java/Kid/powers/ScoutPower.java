package Kid.powers;
import com.megacrit.cardcrawl.actions.watcher.MeditateAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ScoutPower extends BasePower {
		public static final String POWER_ID = "Kid:ScoutPower";

		public ScoutPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			if (isPlayer) {
//				addToTop(new SelectCardsAction(AbstractDungeon.player.discardPile.group, amount, text, false,  c -> true, list -> {
//					for (AbstractCard c : list) {
//						AbstractDungeon.player.hand.addToTop(c);
//						c.retain = true;
//						AbstractDungeon.player.discardPile.removeCard(c);
//					}
//				}
//				));

				addToBot(new MeditateAction(amount));
			}
		}
}
