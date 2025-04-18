package Kid.powers;
import Kid.actions.FlipCardAction;
import Kid.actions.FlipSpecificCardAction;
import Kid.cards.KidCard;
import Kid.cards.KidCard.Strategy;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TrickExposurePower extends BasePower {
		public static final String POWER_ID = "Kid:TrickExposurePower";

		public TrickExposurePower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
		}

		@Override
		public void updateDescription() {
				this.description = DESCRIPTIONS[0];
		}

		@Override
		public void onUseCard(AbstractCard card, UseCardAction action) {
			if (card instanceof KidCard && ((KidCard) card).isReverse()) {
//				((KidCard) card).setFlipped(false);
				addToBot(new FlipSpecificCardAction(card));
			}
			flash();
		}
}
