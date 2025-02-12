package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WhiteCloak extends KidCard {
	public static final String ID = makeID(WhiteCloak.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:WhiteCloak");

	private static final int BLOCK = 5;
	private static final int UPG_BLOCK = 2;

	public WhiteCloak() {
		super(ID, info);

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new SelectCardsInHandAction(10, cardStrings.EXTENDED_DESCRIPTION[0],
				true, true,
				c -> c instanceof KidCard && ((KidCard) c).isReverse(), list -> {
			final int BLOCK = block;

			// 多段防御
			for (int i = 0; i < list.size(); i++) {
				addToBot(new GainBlockAction(p, p, BLOCK));
			}

			// 将list中的卡牌丢弃
			list.forEach(c -> addToBot(new DiscardSpecificCardAction(c)));
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new WhiteCloak();
	}
}