package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.cards.KidCard;
import Kid.character.Kid;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Club extends BaseRelic {
		private static final String NAME = Club.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.COMMON;
		private static final LandingSound SOUND = LandingSound.CLINK;

		public Club() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

		@Override
		public float atDamageModify(float damage, AbstractCard c) {
			if (c instanceof KidCard && ((KidCard) c).isReverse()) {
				return damage + 2;
			}
			return damage;
		}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
