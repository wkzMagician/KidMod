package Kid.cards.common;

import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CardTrick extends KidCard {
	public static final String ID = makeID(CardTrick.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			0
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:CardTrick");

	private static final int REPLACE_AMOUNT = 1;
	private static final int UPG_REPLACE_AMOUNT = 1;

	public CardTrick() {
		super(ID, info);

		setMagic(REPLACE_AMOUNT, UPG_REPLACE_AMOUNT);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DrawCardAction(magicNumber));
		addToBot(new SetCardSideAction(p, p, magicNumber, Strategy.TOP, true));;

		addToBot(new SelectCardsInHandAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0],
				c -> true, list -> {
			for (AbstractCard c : list) {
				if(c instanceof KidCard) {
					((KidCard) c).setFlipped(false);
				}
				// 弃牌
				addToTop(new DiscardSpecificCardAction(c));
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new CardTrick();
	}
}