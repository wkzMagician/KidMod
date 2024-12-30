package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Cheat extends KidCard {
	public static final String ID = makeID(Cheat.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:Cheat");

	public Cheat() {
		super(ID, info);

		setCostUpgrade(0);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0],
				c -> c instanceof KidCard && ((KidCard) c).isReverse(), list -> {
			if (!list.isEmpty()) {
				KidCard c = (KidCard) list.get(0);

				// 这这张牌加入卡牌队列
				// 随机选择一个敌人
				AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, AbstractDungeon.getRandomMonster(), c.energyOnUse, true, true), true);
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Cheat();
	}
}