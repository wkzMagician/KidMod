package Kid.cards.status;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FakeGem extends GemCard {
	public static final String ID = makeID(FakeGem.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.SPECIAL,
			CardTarget.SELF,
			-2
	);

	public FakeGem() {
		super(ID, info);
	}

	@Override
	public void triggerOnEndOfPlayerTurn() {
		addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 2));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new FakeGem();
	}
}