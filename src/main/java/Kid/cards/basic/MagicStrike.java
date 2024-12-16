package Kid.cards.basic;

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

public class MagicStrike extends KidCard {
	public static final String ID = makeID(MagicStrike.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.BASIC,
			CardTarget.ENEMY,
			1
	);

	private static final int DAMAGE = 10;
	private static final int UPG_DAMAGE = 3;

	public MagicStrike() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.

		tags.add(CardTags.STARTER_STRIKE);
		tags.add(CardTags.STRIKE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		addToBot(new SetCardSideAction(p, m, 3, Strategy.RANDOM, true));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new MagicStrike();
	}
}