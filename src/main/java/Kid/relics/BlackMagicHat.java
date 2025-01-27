package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.actions.FlipCardAction;
import Kid.cards.KidCard.Strategy;
import Kid.character.Kid;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlackMagicHat extends BaseRelic {
		private static final String NAME = BlackMagicHat.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.BOSS;
		private static final LandingSound SOUND = LandingSound.CLINK;

		public BlackMagicHat() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

		@Override
	  public void onPlayerEndTurn() {
			super.onPlayerEndTurn();
			addToBot(new FlipCardAction(AbstractDungeon.player, AbstractDungeon.player, 10, Strategy.TOP));
	  }

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
	public boolean canSpawn() {
		return AbstractDungeon.player.hasRelic(WhiteMagicHat.ID);
	}

	@Override
	public void obtain() {
		if (AbstractDungeon.player.hasRelic(WhiteMagicHat.ID)) {
			for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
				if (AbstractDungeon.player.relics.get(i).relicId.equals(WhiteMagicHat.ID)) {
					instantObtain(AbstractDungeon.player, i, true);
					break;
				}
			}
		} else { super.obtain(); }
	}
}
