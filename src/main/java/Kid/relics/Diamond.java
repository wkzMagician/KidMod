package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.cards.KidCard;
import Kid.character.Kid;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Diamond extends BaseRelic {
		private static final String NAME = Diamond.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.UNCOMMON;
		private static final LandingSound SOUND = LandingSound.CLINK;

		public Diamond() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
