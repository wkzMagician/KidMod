package Kid.potions;

import static Kid.KidMod.makeID;

import Kid.actions.FlipCardAction;
import Kid.cards.KidCard;
import Kid.cards.KidCard.Strategy;
import Kid.character.Kid;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class FlipPotion extends BasePotion {
		public static final String ID = makeID(FlipPotion.class.getSimpleName());

		private static final Color LIQUID_COLOR = CardHelper.getColor(255, 255, 255);
		private static final Color HYBRID_COLOR = CardHelper.getColor(0, 0, 0);
//		private static final Color SPOTS_COLOR = CardHelper.getColor(255, 255, 255);
		private static final Color SPOTS_COLOR = null;

		public FlipPotion() {
				super(ID, 0, PotionRarity.COMMON, PotionSize.CARD, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
		}

		@Override
		public void use(AbstractCreature target) {
			if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
				addToBot(new SelectCardsInHandAction(10, DESCRIPTIONS[1], true, true, c -> c instanceof KidCard, list -> {
					for (AbstractCard c : list) {
						((KidCard) c).flip();
					}
				}));
			}
		}

		@Override
		public int getPotency(int ascensionLevel) {
				return this.potency;
		}

	@Override
	public String getDescription() {
		return DESCRIPTIONS[0];
	}

	@Override
		public AbstractPotion makeCopy() {
				return new FlipPotion();

		}
}
