package Kid.cards.status;

import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import basemod.AutoAdd.NotSeen;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Fish extends KidCard {
	public static final String ID = makeID(Fish.class.getSimpleName());
	private static final CardStats info = new CardStats(
			CardColor.COLORLESS,
			CardType.STATUS,
			CardRarity.COMMON,
			CardTarget.SELF,
			-2
	);

	public Fish() {
		super(ID, info);

		setEthereal(true);
	}

	@Override
	public void triggerOnEndOfTurnForPlayingCard() {
		for(AbstractCard c : AbstractDungeon.player.hand.group) {
			if(c instanceof GemCard) {
//				System.out.println("Fish: " + c.name);
				addToTop(new DiscardSpecificCardAction(c));
			}
		}

		super.triggerOnEndOfTurnForPlayingCard();
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Fish();
	}
}