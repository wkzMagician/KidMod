package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PhantomSteal extends KidCard {
	public static final String ID = makeID(PhantomSteal.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY,
			2
	);

	static private final int DAMAGE = 15;
	static private final int UPG_DAMAGE = 20;

	public PhantomSteal() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.BLUNT_LIGHT));

		// 交换抽牌堆与弃牌堆
		CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
		int drawSize = p.drawPile.size();
		int discardSize = p.discardPile.size();
		addToBot(new MoveCardsAction(tmp, p.drawPile, c -> true, drawSize));
		addToBot(new MoveCardsAction(p.drawPile, p.discardPile, c -> true, discardSize));
		addToBot(new MoveCardsAction(p.discardPile, tmp, c -> true, drawSize));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new PhantomSteal();
	}
}