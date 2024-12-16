package Kid.actions;

import Kid.powers.CharmPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class FansSupportAction extends AbstractGameAction {
	private boolean freeToPlayOnce = false;

	private AbstractPlayer p;

	private AbstractMonster m;

	private int energyOnUse = -1;

	private int charm = 0;

	public FansSupportAction(AbstractPlayer p, AbstractMonster m, int charm, boolean freeToPlayOnce, int energyOnUse) {
		this.p = p;
		this.m = m;
		this.freeToPlayOnce = freeToPlayOnce;
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.energyOnUse = energyOnUse;
		this.charm = charm;
	}

	public void update() {
		int effect = EnergyPanel.totalCount;
		if (this.energyOnUse != -1)
			effect = this.energyOnUse;
		if (this.p.hasRelic("Chemical X")) {
			effect += 2;
			this.p.getRelic("Chemical X").flash();
		}

		if (effect > 0) {
			// 获得X层魅力
			int amount = this.charm * effect;
			addToBot(new ApplyPowerAction(p, p, new CharmPower(p, amount), amount));

			if (!this.freeToPlayOnce)
				this.p.energy.use(EnergyPanel.totalCount);
		}
		this.isDone = true;
	}
}
