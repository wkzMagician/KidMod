package Kid.actions;

import Kid.cards.KidCard;
import Kid.cards.KidCard.Strategy;
import Kid.powers.MindMagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.ArrayList;

public class TriggerFlipPowerAction extends AbstractGameAction {
	private AbstractPlayer p;

	private static final float DURATION = Settings.ACTION_DUR_XFAST;

	public TriggerFlipPowerAction(AbstractCreature target, AbstractCreature source) {
		this.p = (AbstractPlayer) target;
		this.source = source;
		this.duration = DURATION;
		this.actionType = ActionType.SPECIAL;
	}

	public void update() {
		if (this.duration == DURATION) {
			// 遍历Power
			for (AbstractPower power : p.powers) {
				// 如果Power名字是MindMagic
				if (power.ID.equals("Kid:MindMagicPower")) {
					MindMagicPower mindMagicPower = (MindMagicPower) power;
					// 调用MindMagicPower的onFlipCard方法
					mindMagicPower.onFlipCard();

				}
			}
		}

		this.isDone = true;
	}
}