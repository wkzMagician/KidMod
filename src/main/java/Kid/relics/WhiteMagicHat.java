package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.cards.KidCard;
import Kid.character.Kid;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class WhiteMagicHat extends BaseRelic {
		private static final String NAME = WhiteMagicHat.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.STARTER;
		private static final LandingSound SOUND = LandingSound.CLINK;

		private static final int FLIP_AMOUNT = 1;

		public WhiteMagicHat() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

		@Override
	  public void onPlayerEndTurn() {
			super.onPlayerEndTurn();
			addToBot(new SelectCardsInHandAction(FLIP_AMOUNT, DESCRIPTIONS[1],
					true, true, card -> card instanceof KidCard, list -> {
				for (AbstractCard c : list) {
					((KidCard) c).flip();
				}
			}));
	  }

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
