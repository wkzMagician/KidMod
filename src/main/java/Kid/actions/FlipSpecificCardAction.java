//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Kid.actions;

import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FlipSpecificCardAction extends AbstractGameAction {
	private AbstractCard targetCard;
	private float startingDuration;

	public FlipSpecificCardAction(AbstractCard targetCard, boolean isFast) {
		this.targetCard = targetCard;
		this.setValues(AbstractDungeon.player, AbstractDungeon.player, this.amount);
		this.actionType = ActionType.SPECIAL;
		this.startingDuration = Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
	}

	public FlipSpecificCardAction(AbstractCard targetCard) {
		this(targetCard, false);
	}

	public void update() {
		if (this.duration == this.startingDuration) {
			if(targetCard instanceof KidCard) {
				((KidCard) targetCard).flip();
			}
		}

		this.tickDuration();
	}
}
