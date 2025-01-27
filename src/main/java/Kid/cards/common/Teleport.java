package Kid.cards.common;

import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.ArrayList;

public class Teleport extends KidCard {
	public static final String ID = makeID(Teleport.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			0
	);

	private static final int CARD_DRAW = 3;
	private static final int UPG_CARD_DRAW = 1;

	public Teleport() {
		super(ID, info);

		setMagic(CARD_DRAW, UPG_CARD_DRAW); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 计算牌堆顶与10的差值
		int amount = 10 - p.hand.size();
		amount = Math.min(amount, magicNumber);

		// 抽牌，并将抽到的牌翻到反面
		addToBot(new DrawCardAction(magicNumber));
		addToBot(new SetCardSideAction(p, p, magicNumber, Strategy.TOP, true));
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
		return new Teleport();
	}
}