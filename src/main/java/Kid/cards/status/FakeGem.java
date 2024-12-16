package Kid.cards.status;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import basemod.AutoAdd.NotSeen;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FakeGem extends GemCard {
	public static final String ID = makeID(FakeGem.class.getSimpleName());
	private static final CardStats info = new CardStats(
			CardColor.COLORLESS,
			CardType.STATUS,
			CardRarity.COMMON,
			CardTarget.SELF,
			-2
	);

	public FakeGem() {
		super(ID, info);
	}

//	@Override
//	public void triggerOnEndOfPlayerTurn() {
//		addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 2));
//	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new FakeGem();
	}
}