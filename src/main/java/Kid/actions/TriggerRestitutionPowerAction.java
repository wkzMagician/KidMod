package Kid.actions;

import Kid.powers.MindMagicPower;
import Kid.powers.RestitutionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TriggerRestitutionPowerAction extends AbstractGameAction {
	private AbstractPlayer p;

	private static final float DURATION = Settings.ACTION_DUR_XFAST;

	public TriggerRestitutionPowerAction(AbstractCreature target, AbstractCreature source) {
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
				if (power.ID.equals("Kid:RestitutionPower")) {
					RestitutionPower restitutionPower = (RestitutionPower) power;
					// 调用MindMagicPower的onFlipCard方法
					restitutionPower.onDiscardGem();

				}
			}
		}

		this.isDone = true;
	}
}