package Kid.powers;
import Kid.cards.GemCard;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Objects;

public class PandoraPower extends BasePower {
		public static final String POWER_ID = "Kid:PandoraPower";

		AbstractCard pandoraCard = null;

		public PandoraPower(AbstractCreature owner, int amount, AbstractCard card) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				pandoraCard = card;
		}

		private boolean otherGemInHand() {
			for(AbstractCard c : AbstractDungeon.player.hand.group) {
				if (c instanceof GemCard && !Objects.equals(c.cardID, "Kid:Pandora")) {
					return true;
				}
			}
			return false;
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
		}

		@Override
		public void atEndOfTurn(boolean isPlayer) {
			flash();
			if(isPlayer) {
				addToBot(new HealAction(owner, owner, this.amount));
			}

			if(otherGemInHand()) {
				for (AbstractCard c : AbstractDungeon.player.hand.group) {
					if (c instanceof GemCard && !Objects.equals(c.cardID, "Kid:Pandora")) {
						// 丢弃
						addToBot(new DiscardSpecificCardAction(pandoraCard));
						break;
					}
				}
			}
		}

//	@Override
//	public void onDrawOrDiscard() {
//		if(otherGemInHand()) {
//			for (AbstractCard c : AbstractDungeon.player.hand.group) {
//				if (c.cardID.equals("Kid:Pandora")) {
//					// 丢弃
//					addToBot(new DiscardSpecificCardAction(c));
//					break;
//				}
//			}
//		}
//	}

}
