package hermit.powers;

import Kid.powers.BasePower;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

// 借鉴了崩坠mod中的实现

public class Drained extends BasePower {
//	public AbstractCreature source;

//	public static final String POWER_ID = HermitMod.makeID("Drained");
//	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
//	public static final String NAME = powerStrings.NAME;
//	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//
//	private static final Texture tex84 = TextureLoader.getTexture("hermitResources/images/powers/hold_barred_p.png");
//	private static final Texture tex32 = TextureLoader.getTexture("hermitResources/images/powers/hold_barred.png");

	public static final String POWER_ID = "Kid:Drained";
	private static final String NAME = "Drained";
	public static final String[] DESCRIPTIONS = new String[] {
			"At the start of your turn, lose #b",
			" #yEnergy."
	};

	public Drained(AbstractCreature owner, int amount) {
		super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
		this.name = NAME;
		this.ID = POWER_ID;
		this.amount = amount;
		this.updateDescription();
		this.loadRegion("Drained");
	}

	@Override
	public void atStartOfTurnPostDraw() { // At the start of your turn
		this.flash();
		this.addToBot(new LoseEnergyAction(amount));
		this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, Drained.POWER_ID));

		updateDescription();
	}

	@Override
	public void updateDescription() {

		description = DESCRIPTIONS[0] + (amount) + DESCRIPTIONS[1];
	}
}