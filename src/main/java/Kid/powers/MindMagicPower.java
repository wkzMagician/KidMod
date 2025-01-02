package Kid.powers;
import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MindMagicPower extends BasePower {
		public static final String POWER_ID = "Kid:MindMagicPower";
		public static final String NAME = "Mind Magic";
		public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

		public MindMagicPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("MindMagic");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
		}

		public void onFlipCard() {
			this.flash();
			addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
		}
}
