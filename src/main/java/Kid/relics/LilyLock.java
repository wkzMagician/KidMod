package Kid.relics;

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
import Kid.character.Kid;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

public class LilyLock extends BaseRelic {
		private static final String NAME = LilyLock.class.getSimpleName();
		public static final String ID = makeID(NAME);

		private static final RelicTier RARITY = RelicTier.BOSS;
		private static final LandingSound SOUND = LandingSound.CLINK;

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

		public LilyLock() {
				super(ID, NAME, Kid.Meta.CARD_COLOR, RARITY, SOUND);
		}

		@Override
	  public void atBattleStartPreDraw() {
			super.atBattleStartPreDraw();
			this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

			// 把一张随机宝石牌加入手牌
			int random_idx = AbstractDungeon.cardRandomRng.random(cards.length - 1);
			GemCard card = (GemCard) cards[random_idx].makeCopy();
			card.addPower();

			this.addToBot(new MakeTempCardInHandAction(card));
		}

	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
}
