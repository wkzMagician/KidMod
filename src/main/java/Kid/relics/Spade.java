package Kid.relics;

import static Kid.KidMod.makeID;

import Kid.cards.KidCard;
import Kid.character.Kid;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class Spade extends BaseRelic {
		private static final String NAME = Spade.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.UNCOMMON;
		private static final LandingSound SOUND = LandingSound.CLINK;

		private static final int ARMOR_AMOUNT = 4;

		public Spade() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

		@Override
	  public void atBattleStart() {
			super.atBattleStart();
			addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, ARMOR_AMOUNT)));
		}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
