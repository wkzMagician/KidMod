package Kid.powers;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class DarkStarPower extends BasePower {
		public static final String POWER_ID = "Kid:DarkStarPower";
		public static final String NAME = "Dark Star";
		public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

		public DarkStarPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("DarkStar");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();

				applyPower();
		}

		private void applyPower() {
			addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
			addToBot(new ApplyPowerAction(this.owner, this.owner, new LoseStrengthPower(this.owner, this.amount), this.amount));
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
		}

		@Override
		public void atStartOfTurn() {
			applyPower();
		}

		@Override
		public void wasHPLost(DamageInfo info, int damageAmount) {
			// 丢弃手中的“黑暗星辰”牌
			// 遍历手牌
			for (AbstractCard c : AbstractDungeon.player.hand.group) {
				// 找到黑暗星辰
				if (c.cardID.equals("Kid:DarkStar")) {
					// 丢弃
					addToBot(new DiscardSpecificCardAction(c));
					break;
				}
			}

			super.wasHPLost(info, damageAmount);
		}
}
