package Kid.powers;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import java.util.ArrayList;

public class ElfLipsPower extends BasePower {
		public static final String POWER_ID = "Kid:ElfLipsPower";
		public static final String NAME = "Elf Lips";
		public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

		private ArrayList<AbstractCard> markedCards = new ArrayList<>();

		public ElfLipsPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("ElfLips");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();

				applyPower();
		}

		@Override
		public void updateDescription() {

			if(this.amount == -1) {
					this.description = DESCRIPTIONS[0];
			} else this.description = DESCRIPTIONS[1];
		}

		private void applyPower() {
			// 清除之前标记
			for(AbstractCard c : markedCards) {
				c.glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);
			}
			markedCards.clear();

			for(AbstractCard c : AbstractDungeon.player.hand.group) {
				if (c.costForTurn == 0) {
					if (!c.glowColor.equals(Color.GOLD.cpy())) {
						// 粉色
						markedCards.add(c);
						c.glowColor = new Color(1.0F, 0.753F, 0.796F, 0.25F);
					}
				}

				if (amount == -2) {
					if (c.costForTurn == 2) {
						if (!c.glowColor.equals(Color.GOLD.cpy())) {
							// 绿色
							markedCards.add(c);
							c.glowColor = new Color(0.0F, 1.0F, 0.0F, 0.25F);
						}
					}
					if (c.costForTurn == 3) {
						if (!c.glowColor.equals(Color.GOLD.cpy())) {
							// 红色
							markedCards.add(c);
							c.glowColor = new Color(1.0F, 0.0F, 0.0F, 0.25F);
						}
					}
				}
			}
		}

		@Override
		public void atStartOfTurn() {
			applyPower();
		}

		@Override
		public void onDrawOrDiscard() {
			applyPower();
		}
}
