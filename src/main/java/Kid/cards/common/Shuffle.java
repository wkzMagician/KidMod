package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.actions.SetCardSideAction;
import Kid.actions.SetCardSideRandomAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Shuffle extends KidCard {
	public static final String ID = makeID(Shuffle.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			0
	);

	public Shuffle() {
		super(ID, info);

		setExhaust(true, false);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
//		addToBot(new SetCardSideRandomAction(p, p, 10, Strategy.TOP));
		addToBot(new FlipCardAction(p, p, 10, Strategy.TOP));
	}


	@Override
	public AbstractCard makeCopy() { //Optional
		return new Shuffle();
	}
}