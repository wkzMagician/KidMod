package Kid.cards.rare;

import Kid.actions.DrawAndFlipToBackAction;
import Kid.actions.FlipCardDrawAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OmnipotentPocket extends KidCard {
	public static final String ID = makeID(OmnipotentPocket.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			1
	);

	public OmnipotentPocket() {
		super(ID, info);
		setCostUpgrade(0);
		setInnate(true);
		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
//		addToBot(new FlipCardDrawAction(10 - p.hand.size()));
		addToBot(new DrawAndFlipToBackAction(10 - p.hand.size() + 1));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new OmnipotentPocket();
	}
}