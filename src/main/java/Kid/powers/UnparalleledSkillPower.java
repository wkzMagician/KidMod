package Kid.powers;
import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class UnparalleledSkillPower extends BasePower {
		public static final String POWER_ID = "Kid:UnparalleledSkillPower";
		public static final String NAME = "Unparalleled Skill";
		public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

		public UnparalleledSkillPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("UnparalleledSkill");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0];
		}

		@Override
		public void onCardDraw(AbstractCard c) {
			if (c instanceof KidCard) {
				((KidCard) c).setFlipped(true);
			}
		}
}
