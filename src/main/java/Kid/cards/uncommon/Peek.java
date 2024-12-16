package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Peek extends KidCard {
	public static final String ID = makeID(Peek.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final int VULNERABLE = 2;
	private static final int UPGRADE_PLUS_VULNERABLE = -1;

	public Peek() {
		super(ID, info);

		setMagic(VULNERABLE, UPGRADE_PLUS_VULNERABLE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainEnergyAction(3));
		addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, magicNumber, false), magicNumber));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Peek();
	}
}