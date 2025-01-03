package Kid.powers;
import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SneakPower extends BasePower {
		public static final String POWER_ID = "Kid:SneakPower";

		public SneakPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
		}

		@Override
		public void updateDescription() {
				this.description = DESCRIPTIONS[0];
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			if (isPlayer) {
				for (AbstractCard c : AbstractDungeon.player.hand.group) {
					if (!c.isEthereal && c instanceof KidCard && ((KidCard) c).isReverse()) {
						c.retain = true;
					}
				}
			}

			addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
		}
}
