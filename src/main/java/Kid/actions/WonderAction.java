package Kid.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import Kid.cards.KidCard;

public class WonderAction extends AbstractGameAction {
	private AbstractPlayer p;

	public WonderAction(AbstractCreature source) {
		setValues((AbstractCreature)AbstractDungeon.player, source, -1);
		this.actionType = ActionType.POWER;
	}

	public void update() {
		for(AbstractCard c : AbstractDungeon.player.hand.group){
			if(c instanceof KidCard && ((KidCard) c).isReverse() && c.costForTurn > 0){
				c.setCostForTurn(0);
			}
		}

		this.isDone = true;
	}
}
