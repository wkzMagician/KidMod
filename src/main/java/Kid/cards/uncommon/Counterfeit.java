package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Counterfeit extends KidCard {
	public static final String ID = makeID(Counterfeit.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY,
			1
	);

	private static final int DAMAGE = 9;
	private static final int UPG_DAMAGE = 3;

	public Counterfeit() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
		// 随机复制1张手牌
		AbstractCard c = p.hand.getRandomCard(true);
		if (c != null) {
			AbstractCard copy = c.makeStatEquivalentCopy();
			p.hand.addToTop(copy);
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Counterfeit();
	}
}