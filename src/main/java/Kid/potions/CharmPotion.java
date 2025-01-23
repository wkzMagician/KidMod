package Kid.potions;

import static Kid.KidMod.makeID;

import Kid.cards.KidCard;
import Kid.powers.CharmPower;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class CharmPotion extends BasePotion {
		public static final String ID = makeID(CharmPotion.class.getSimpleName());

		private static final Color LIQUID_COLOR = CardHelper.getColor(255, 0, 255);
		private static final Color HYBRID_COLOR = CardHelper.getColor(255, 0, 255);
		private static final Color SPOTS_COLOR = CardHelper.getColor(255, 0, 255);

		public CharmPotion() {
				super(ID, 8, PotionRarity.COMMON, PotionSize.BOLT, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
		}

		@Override
		public void use(AbstractCreature target) {
			if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
				addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CharmPower(AbstractDungeon.player, this.potency), this.potency));
			}
		}

		@Override
		public int getPotency(int ascensionLevel) {
				return 8;
		}

	@Override
	public String getDescription() {
		return DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
	}

	@Override
		public AbstractPotion makeCopy() {
				return new CharmPotion();

		}
}
