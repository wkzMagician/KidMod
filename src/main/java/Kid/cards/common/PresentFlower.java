package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.CharmPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PresentFlower extends KidCard {
	public static final String ID = makeID(PresentFlower.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			1
	);

	private static final int DAMAGE = 8;
	private static final int UPG_DAMAGE = 2;

	private static final int CHARM_AMOUNT = 4;
	private static final int UPG_CHARM_AMOUNT = 2;

	public PresentFlower() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
		setMagic(CHARM_AMOUNT, UPG_CHARM_AMOUNT); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		addToBot(new ApplyPowerAction(m, p, new CharmPower(m, magicNumber), magicNumber));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new PresentFlower();
	}
}