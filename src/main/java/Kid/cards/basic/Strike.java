package Kid.cards.basic;

import Kid.cards.KidCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Kid.cards.BaseCard;
import Kid.character.Kid;
import Kid.util.CardStats;

public class Strike extends KidCard {
	public static final String ID = makeID(Strike.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.BASIC,
			CardTarget.ENEMY,
			1
	);

	private static final int DAMAGE = 6;
	private static final int UPG_DAMAGE = 3;

	public Strike() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.

		tags.add(CardTags.STARTER_STRIKE);
		tags.add(CardTags.STRIKE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Strike();
	}
}