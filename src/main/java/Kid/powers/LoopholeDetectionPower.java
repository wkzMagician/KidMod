package Kid.powers;
import Kid.cards.KidCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LoopholeDetectionPower extends BasePower {
		public static final String POWER_ID = "Kid:LoopholeDetectionPower";

		private AbstractCard markedCard = null;

		public LoopholeDetectionPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);

//				this.remark();

			// 标记所有牌堆中的BASIC卡牌
			for(AbstractCard c : AbstractDungeon.player.drawPile.group){
				if(c instanceof KidCard && c.rarity == AbstractCard.CardRarity.BASIC){
					((KidCard)c).setMarked(true);
				}
			}
			for(AbstractCard c : AbstractDungeon.player.discardPile.group){
				if(c instanceof KidCard && c.rarity == AbstractCard.CardRarity.BASIC){
					((KidCard)c).setMarked(true);
				}
			}
			for(AbstractCard c : AbstractDungeon.player.hand.group){
				if(c instanceof KidCard && c.rarity == AbstractCard.CardRarity.BASIC){
					((KidCard)c).setMarked(true);
				}
			}
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0];
		}
}
