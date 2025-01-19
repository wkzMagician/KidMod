package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.ReasonPower;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
			0
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:Reason");

	private static final int ENERGY = 1;
	private static final int UPGRADE_PLUS_ENERGY = 1;

	public Reason() {
		super(ID, info);
		setMagic(ENERGY, UPGRADE_PLUS_ENERGY);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// * 1.从牌组中选择1张牌

		addToBot(new SelectCardsAction(p.masterDeck.group, 1, cardStrings.EXTENDED_DESCRIPTION[0],
				false, c -> true, list -> {
			if(list.isEmpty()) return;

			AbstractCard card = list.get(0);
			// * 2.将选择的牌记录在ReasonPower中
			addToBot(new ApplyPowerAction(p, p, new ReasonPower(p, card, magicNumber), magicNumber));
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Reason();
	}
}