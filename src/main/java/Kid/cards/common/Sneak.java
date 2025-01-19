package Kid.cards.common;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.SneakPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sneak extends KidCard {
	public static final String ID = makeID(Sneak.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			2
	);

	private static final int BLOCK = 14;
	private static final int UPG_BLOCK = 4;

	public Sneak() {
		super(ID, info);

		setBlock(BLOCK, UPG_BLOCK);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, block));
		// 保留手中所有反面牌
		addToBot(new ApplyPowerAction(p, p, new SneakPower(p, -1)));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Sneak();
	}
}