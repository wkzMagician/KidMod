package Kid.powers;
import Kid.cards.KidCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.MeditateAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

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
