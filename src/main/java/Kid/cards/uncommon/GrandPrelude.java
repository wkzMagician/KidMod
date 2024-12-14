package Kid.cards.uncommon;

import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.CharmPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GrandPrelude extends KidCard {
	public static final String ID = makeID(GrandPrelude.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.UNCOMMON,
			CardTarget.ALL_ENEMY,
			0
	);

	private static final int CHARM = 6;
	private static final int UPG_CHARM = 3;

	public GrandPrelude() {
		super(ID, info);

		setInnate(true);
		setExhaust(true);

		setMagic(CHARM, UPG_CHARM);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
			addToBot(new ApplyPowerAction(p, p, new CharmPower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new GrandPrelude();
	}
}