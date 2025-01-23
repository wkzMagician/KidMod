package Kid.potions;

import static Kid.KidMod.makeID;

import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.cards.rare.BloodTears;
import Kid.cards.rare.ParisSunshine;
import Kid.cards.rare.TowerDiamond;
import Kid.cards.status.FakeGem;
import Kid.cards.uncommon.BlueBirthday;
import Kid.cards.uncommon.DarkStar;
import Kid.cards.uncommon.ElfLips;
import Kid.cards.uncommon.MotherOfGems;
import Kid.cards.uncommon.PupilOfMoon;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class AlchemyPotion extends BasePotion {
		public static final String ID = makeID(AlchemyPotion.class.getSimpleName());

		private static final Color LIQUID_COLOR = CardHelper.getColor(255, 255, 51);
		private static final Color HYBRID_COLOR = CardHelper.getColor(255, 153, 51);
//		private static final Color SPOTS_COLOR = CardHelper.getColor(255, 102, 102);
		private static final Color SPOTS_COLOR = null;

	// 可能打印的卡牌列表
	private static final String[] cards_string = {
			"Kid:DarkStar",
			"Kid:PupilOfMoon",
			"Kid:ElfLips",
			"Kid:MotherOfGems",
			"Kid:BloodTears",
			"Kid:BlueBirthday",
			"Kid:TowerDiamond",
			"Kid:ParisSunshine"
	};

	private static final AbstractCard[] cards = {
			new DarkStar(),
			new PupilOfMoon(),
			new FakeGem(),
			new ElfLips(),
			new MotherOfGems(),
			new BloodTears(),
			new BlueBirthday(),
			new TowerDiamond(),
			new ParisSunshine()
	};

		public AlchemyPotion() {
				super(ID, 1, PotionRarity.COMMON, PotionSize.BOTTLE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
		}

		@Override
		public void use(AbstractCreature target) {
			if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
				for (int i = 0; i < this.potency; i++) {
					// 随机
					int random_idx = AbstractDungeon.cardRandomRng.random(cards.length - 1);
					AbstractCard card = cards[random_idx].makeCopy();
					addToBot(new MakeTempCardInHandAction(card));
					((GemCard) card).addPower();
				}
			}
		}

		@Override
		public int getPotency(int ascensionLevel) {
				return 1;
		}

	@Override
	public String getDescription() {
		if(this.potency == 1) {
			return DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
		} else {
			return DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[2];
		}
	}

	@Override
		public AbstractPotion makeCopy() {
				return new AlchemyPotion();

		}
}
