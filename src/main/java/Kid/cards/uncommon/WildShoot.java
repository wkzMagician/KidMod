package Kid.cards.uncommon;

import Kid.actions.WildShootAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WildShoot extends KidCard {
	public static final String ID = makeID(WildShoot.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.RARE,
			CardTarget.ENEMY,
			-1
	);

	private static final int DAMAGE_PER_CARD = 10;
	private static final int UPGRADE_DAMAGE = 0;

	public WildShoot() {
		super(ID, info);

		setDamage(DAMAGE_PER_CARD, UPGRADE_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new WildShootAction(p, m, this.damage, this.upgraded, this.freeToPlayOnce, this.energyOnUse));

	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new WildShoot();
	}
}