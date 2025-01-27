package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.DisguisePower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Heart extends BaseRelic {
		private static final String NAME = Heart.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.RARE;
		private static final LandingSound SOUND = LandingSound.CLINK;

		private static final int FLIP_AMOUNT = 1;

		public Heart() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

		@Override
	  public void atBattleStart() {
			super.atBattleStart();
			addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DisguisePower(
					AbstractDungeon.player, 2)));
		}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
