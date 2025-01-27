package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.character.Kid;
import Kid.powers.DisguisePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Rose extends BaseRelic {
		private static final String NAME = Rose.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.RARE;
		private static final LandingSound SOUND = LandingSound.CLINK;

		public Rose() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
