package Kid.cards.common;

import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Vanish extends KidCard {
	public static final String ID = makeID(Vanish.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			1
	);

	private static final int BLOCK = 8;
	private static final int UPG_BLOCK = 3;

	public Vanish() {
		super(ID, info);

		setMagic(2); //Sets the card's magic number and how much it changes when upgraded.
		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int amount = 10 - p.hand.size();
		amount = Math.min(amount, magicNumber);

		addToBot(new GainBlockAction(p, p, block));
		addToBot(new DrawCardAction(magicNumber));
		addToBot(new SetCardSideAction(p, p, amount, Strategy.TOP, true));
		if(magicNumber > amount){
			for(int i = 0; i < magicNumber - amount; i++){
				AbstractCard c = p.discardPile.getNCardFromTop(i);
				if(c instanceof KidCard){
					((KidCard) c).setFlipped(true);
				}
			}
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Vanish();
	}
}