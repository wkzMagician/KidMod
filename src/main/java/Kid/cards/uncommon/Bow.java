package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Bow extends KidCard {
	public static final String ID = makeID(Bow.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			0
	);

	private static final int DISCARD = 3;
	private static final int UPG_DISCARD = -1;

	private static final int CHARM = 6;

	public Bow() {
		super(ID, info);

		setMagic(DISCARD, UPG_DISCARD);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DiscardAction(p, p, this.magicNumber, false));
		// 获得魅力
		addToBot(new ApplyPowerAction(p, p, new Kid.powers.CharmPower(p, CHARM), CHARM));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Bow();
	}
}