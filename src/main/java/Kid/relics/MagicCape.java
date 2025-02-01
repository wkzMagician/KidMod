package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.cards.KidCard;
import Kid.character.Kid;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class MagicCape extends BaseRelic {
		private static final String NAME = MagicCape.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.BOSS;
		private static final LandingSound SOUND = LandingSound.CLINK;

	private boolean triggeredThisTurn = false;

	public MagicCape() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

	@Override
	public void atTurnStart() {
		super.atTurnStart();
		this.triggeredThisTurn = false;
	}

	@Override
	public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
		super.onUseCard(targetCard, useCardAction);
		if(targetCard instanceof KidCard && ((KidCard) targetCard).isReverse()){
			if(triggeredThisTurn) return;

			this.flash();
			this.triggeredThisTurn = true;
			addToBot(new GainEnergyAction(1));
		}
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
