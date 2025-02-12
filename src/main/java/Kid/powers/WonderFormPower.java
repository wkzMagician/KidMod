package Kid.powers;
import Kid.actions.WonderAction;
import Kid.cards.KidCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;

public class WonderFormPower extends BasePower {
		public static final String POWER_ID = "Kid:WonderFormPower";

		public WonderFormPower(AbstractCreature owner) {
				super(POWER_ID, PowerType.BUFF, false, owner, -1);
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

//	@Override
//	public void onCardDraw(AbstractCard card) {
//		if(card instanceof KidCard && ((KidCard) card).isReverse() && card.costForTurn > 0) {
//			flash();
//			card.setCostForTurn(card.costForTurn - 1);
//		}
//	}

	@Override
	public void atStartOfTurnPostDraw() {
			super.atStartOfTurnPostDraw();

			this.flash();

			addToBot(new WonderAction(owner));

//			for(AbstractCard c : AbstractDungeon.player.hand.group){
//				if(c instanceof KidCard && ((KidCard) c).isReverse() && c.costForTurn > 0){
//					flash();
//					c.setCostForTurn(c.costForTurn - 1);
//				}
//			}
	}

}
