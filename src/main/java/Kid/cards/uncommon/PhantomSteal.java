package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PhantomSteal extends KidCard {
	public static final String ID = makeID(PhantomSteal.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);
	public PhantomSteal() {
		super(ID, info);

		setExhaust(true);
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();

			setExhaust(false);
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 交换抽牌堆与弃牌堆
		CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
		int drawSize = p.drawPile.size();
		int discardSize = p.discardPile.size();
		addToBot(new MoveCardsAction(tmp, p.drawPile, c -> true, drawSize));
		addToBot(new MoveCardsAction(p.drawPile, p.discardPile, c -> true, discardSize));
		addToBot(new MoveCardsAction(p.discardPile, tmp, c -> true, drawSize));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new PhantomSteal();
	}
}