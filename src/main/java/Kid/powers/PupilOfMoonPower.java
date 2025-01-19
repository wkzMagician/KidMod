package Kid.powers;
import Kid.cards.KidCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class PupilOfMoonPower extends BasePower {
		public static final String POWER_ID = "Kid:PupilOfMoonPower";

		public PupilOfMoonPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);

				applyPower();
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
		}

	private void applyPower() {
		addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
		addToBot(new ApplyPowerAction(this.owner, this.owner, new LoseDexterityPower(this.owner, this.amount), this.amount));

		// 如果能力层数为0
	}

	@Override
	public void atStartOfTurn() {
		applyPower();
	}

	// 造成伤害
	@Override
	public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
		super.onAttack(info, damageAmount, target);

		if(damageAmount <= 0 ) return;

		// 遍历手牌
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (c.cardID.equals("Kid:PupilOfMoon")) {
				// 丢弃
				addToBot(new DiscardSpecificCardAction(c));
				break;
			}
		}
	}
}
