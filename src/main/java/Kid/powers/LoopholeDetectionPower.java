package Kid.powers;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LoopholeDetectionPower extends BasePower {
		public static final String POWER_ID = "Kid:LoopholeDetectionPower";
		public static final String NAME = "Loophole Detection";
		public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

		private AbstractCard markedCard = null;

		public LoopholeDetectionPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("LoopholeDetection");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();

				this.remark();
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0];
		}

		// 首次检测
		private void remark() {
				if(markedCard != null) {
					markedCard.glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);
				}
				markedCard = null;

				int maxCost = 0;

				// 遍历手牌
				for (AbstractCard c : AbstractDungeon.player.hand.group) {
					// 找到最大费用
					if (c.costForTurn > maxCost) {
						maxCost = c.costForTurn;
						markedCard = c;
					}
				}

				// 高亮
				if (markedCard != null) {
					markedCard.glowColor = Color.GOLD.cpy();
				}
		}

		@Override
		public void onDrawOrDiscard() {
			remark();
		}
}
