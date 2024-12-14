package Kid.cards;

import Kid.actions.TriggerFlipPowerAction;
import Kid.actions.TriggerRestitutionPowerAction;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.lwjgl.Sys;

public abstract class GemCard extends KidCard {
	// 宝石牌：一般无法打出
	// 需要重写其onDiscard以及onDraw方法,onExhaust方法

	protected boolean hasPower = false;

	// 构造方法
	public GemCard(String id, CardStats stats) {
		super(id, stats);
		setSelfRetain(true);
	}

	public void removePower() {
		hasPower = false;
	}

	public void addPower() {
		hasPower = true;
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		this.cantUseMessage = "无法打出宝石牌";
		return false;
	}

	@Override
	public void triggerWhenDrawn() {
		addPower();
	}

	@Override
	public void triggerOnManualDiscard() {
		removePower();
	}

	@Override
	public void triggerOnExhaust() {
		removePower();
	}

	@Override
	public void triggerOnEndOfPlayerTurn() {
//		if(!this.retain){
//			removePower();
//		}
	}

	@Override
	public void atTurnStart() {
		super.atTurnStart();
		if (!this.hasPower && isInHand()) {
			this.addPower();
		}
	}

	public boolean isInHand() {
		for(AbstractCard c : AbstractDungeon.player.hand.group) {
			if(c == this) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onMoveToDiscard() {
		super.onMoveToDiscard();
		removePower();

		addToTop(new TriggerRestitutionPowerAction(AbstractDungeon.player, AbstractDungeon.player));
	}
}