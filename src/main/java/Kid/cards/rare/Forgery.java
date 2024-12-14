package Kid.cards.rare;

import Kid.actions.FlipCardDrawAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Forgery extends KidCard {
	public static final String ID = makeID(Forgery.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			1
	);

	private static final int MAGIC = 2;
	private static final int UPG_MAGIC = 1;

	public Forgery() {
		super(ID, info);

		setExhaust(true);

		setMagic(MAGIC, UPG_MAGIC);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new SelectCardsInHandAction(1, "选择一张手牌", c -> true, list -> {
			AbstractCard c = list.get(0);
			for (int i = 0; i < this.magicNumber; i++) {
				AbstractCard copy = c.makeStatEquivalentCopy();
				copy.isEthereal = true;
				p.hand.addToTop(copy);
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Forgery();
	}
}