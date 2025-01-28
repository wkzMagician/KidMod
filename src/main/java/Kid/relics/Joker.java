package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.cards.KidCard;
import Kid.character.Kid;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Joker extends BaseRelic {
		private static final String NAME = Joker.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.SHOP;
		private static final LandingSound SOUND = LandingSound.CLINK;

		private static final int ADDITIONAL_DRAW = 2;

		public Joker() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

	@Override
	public void onEquip() {
		AbstractDungeon.player.masterHandSize += ADDITIONAL_DRAW;
	}

	@Override
	public void onUnequip() {
		AbstractDungeon.player.masterHandSize -= ADDITIONAL_DRAW;
	}

	@Override
	public void atTurnStart() {
		flash();
	}

	@Override
	public void onCardDraw(AbstractCard drawnCard) {
		if (drawnCard instanceof KidCard) {
			flash();
			((KidCard) drawnCard).setFlipped(true);
		}
	}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
