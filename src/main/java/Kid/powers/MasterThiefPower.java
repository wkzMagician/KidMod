package Kid.powers;
import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.cards.status.FakeGem;
import Kid.cards.rare.BloodTears;
import Kid.cards.rare.ParisSunshine;
import Kid.cards.rare.TowerDiamond;
import Kid.cards.uncommon.BlueBirthday;
import Kid.cards.uncommon.DarkStar;
import Kid.cards.uncommon.ElfLips;
import Kid.cards.uncommon.MotherOfGems;
import Kid.cards.uncommon.PupilOfMoon;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MasterThiefPower extends BasePower {
		public static final String POWER_ID = "Kid:MasterThiefPower";
		public static final String NAME = "Master Thief";
		public static final String[] DESCRIPTIONS = new String[] {
						"At the end of your turn, add ",
				" random Gem to your hand.",
				" random Gems to your hand."
		};

		// 可能打印的卡牌列表
		private static final String[] cards_string = {
				"Kid:DarkStar",
				"Kid:PupilOfMoon",
				"Kid:FakeGem",
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

		public MasterThiefPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
				this.loadRegion("MasterThief");
				this.name = NAME;
				this.amount = amount;
				this.updateDescription();
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0] + this.amount + (this.amount == 1 ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
		}

		@Override
		public void atStartOfTurn() {
			for (int i = 0; i < this.amount; i++) {
				// 随机
				int random_idx = AbstractDungeon.cardRandomRng.random(cards.length - 1);
				AbstractCard card = cards[random_idx].makeCopy();
				addToBot(new MakeTempCardInHandAction(card));
				((GemCard) card).addPower();
			}
		}
}
