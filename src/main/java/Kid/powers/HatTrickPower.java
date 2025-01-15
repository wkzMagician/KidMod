package Kid.powers;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HatTrickPower extends BasePower {
		public static final String POWER_ID = "Kid:HatTrickPower";

		private int damage;

		private AbstractCard firstCard;

		public HatTrickPower(AbstractCreature owner, int magicNumber) {
				super(POWER_ID, PowerType.BUFF, false, owner, 0);
				this.amount = 0;
				this.damage = magicNumber;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.damage + DESCRIPTIONS[1];
		}

		@Override
		public void onUseCard(AbstractCard card, UseCardAction action) {
			if(card.cardID.equals("Kid:RepeatTrick")) {
				return;
			}

			if (this.firstCard == null) {
				this.firstCard = card;
				this.amount = 1;
			} else if (this.firstCard.cardID.equals(card.cardID)) {
				this.amount++;
				if (this.amount == 3) {
					this.amount = 0;
					addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.damage, DamageType.HP_LOSS, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
			} else {
				this.firstCard = card;
				this.amount = 1;
			}
		}

}
