package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.character.Kid;

public class Monocle extends BaseRelic {
		private static final String NAME = Monocle.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.RARE;
		private static final LandingSound SOUND = LandingSound.CLINK;

		public Monocle() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
