package Kid.cards.Status;

import Kid.actions.SetCardSideAction;
import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fish extends KidCard {
	public static final String ID = makeID(Fish.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
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
	public void triggerOnEndOfPlayerTurn() {
		for(AbstractCard c : AbstractDungeon.player.hand.group) {
			if(c instanceof GemCard) {
				addToBot(new DiscardSpecificCardAction(c));
			}
		}

		super.triggerOnEndOfPlayerTurn();
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Fish();
	}
}