package Kid.powers;
import Kid.actions.FlipCardAction;
import Kid.cards.KidCard;
import Kid.cards.KidCard.Strategy;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UnparalleledSkillPower extends BasePower {
		public static final String POWER_ID = "Kid:UnparalleledSkillPower";

		public UnparalleledSkillPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0];
		}

		@Override
		public void onUseCard(AbstractCard card, UseCardAction action) {
			super.onUseCard(card, action);
			flash();
			addToBot(new FlipCardAction(AbstractDungeon.player, AbstractDungeon.player, 1, Strategy.RANDOM));
		}
}
