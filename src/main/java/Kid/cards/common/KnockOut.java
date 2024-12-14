package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KnockOut extends KidCard {
	public static final String ID = makeID(KnockOut.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			2
	);

	private static final int DAMAGE = 20;
	private static final int UPG_DAMAGE = 6;


	public KnockOut() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		addToBot(new SetCardSideAction(p, p, 10, Strategy.TOP, true));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new KnockOut();
	}
}