package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Reason extends KidCard {
	public static final String ID = makeID(Reason.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:Reason");

	public Reason() {
		super(ID, info);

		setCostUpgrade(0);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// * 1.从牌组中选择1张牌
		// * 2. 遍历3个牌堆，将所有同名的牌翻到正面

		addToBot(new SelectCardsAction(p.masterDeck.group, 1, cardStrings.EXTENDED_DESCRIPTION[0],
				false, c -> true, list -> {
			AbstractCard card = list.get(0);
			for (AbstractCard c : p.drawPile.group) {
				if (c.cardID.equals(card.cardID)) {
					if(c instanceof KidCard){
						((KidCard) c).setFlipped(false);
					}
				}
			}
			for (AbstractCard c : p.hand.group) {
				if (c.cardID.equals(card.cardID)) {
					if(c instanceof KidCard){
						((KidCard) c).setFlipped(false);
					}
				}
			}
			for (AbstractCard c : p.discardPile.group) {
				if (c.cardID.equals(card.cardID)) {
					if(c instanceof KidCard){
						((KidCard) c).setFlipped(false);
					}
				}
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Reason();
	}
}