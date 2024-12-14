package Kid.powers;
import Kid.cards.KidCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;

public class WonderFormPower extends BasePower {
		public static final String POWER_ID = "Kid:WonderFormPower";
		public static final String NAME = "Wonder Form";
		public static final String[] DESCRIPTIONS = new String[] {
						"When you draw a reversed card, it costs 1 less energy."
		};

		public WonderFormPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("WonderForm");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
				this.description = DESCRIPTIONS[0];
		}

//	@Override
//	public void onCardDraw(AbstractCard c) {
//		if (c instanceof KidCard && ((KidCard) c).isReverse()) {
//			flash();
//			c.setCostForTurn(c.costForTurn - 1);
//		}
//	}

	@Override
	public void onCardDraw(AbstractCard card) {
		if(card instanceof KidCard && ((KidCard) card).isReverse()) {
			flash();
			card.setCostForTurn(card.costForTurn - 1);
		}
	}

}
