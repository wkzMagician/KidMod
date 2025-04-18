package Kid.powers;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class RestitutionPower extends BasePower {
		public static final String POWER_ID = "Kid:RestitutionPower";

		public RestitutionPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
		}

		public void onDiscardGem() {
			this.flash();
//			addToBot(new DamageRandomEnemyAction(new DamageInfo(this.owner, this.amount, DamageType.NORMAL), AttackEffect.FIRE));
			addToBot(new DrawCardAction(this.amount));
		}
}
