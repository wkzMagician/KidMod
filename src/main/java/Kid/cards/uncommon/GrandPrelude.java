package Kid.cards.uncommon;

import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

	private static final int DAMAGE = 12;

	public GrandPrelude() {
		super(ID, info);

		setDamage(DAMAGE);
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			// 固有
			setInnate(true);
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
			addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		// 如果弃牌堆为空
		if (p.discardPile.isEmpty())
			return true;
		// 否则不能使用
		cantUseMessage = "Discard pile is not empty.";
		return false;
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new GrandPrelude();
	}
}