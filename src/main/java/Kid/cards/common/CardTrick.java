package Kid.cards.common;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CardTrick extends KidCard {
	public static final String ID = makeID(CardTrick.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			2
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:CardTrick");

	private static final int BASE_DAMAGE = 12;

	private static final int INCREASE_DAMAGE = 1;
	private static final int UPGRADE_INCREASE_DAMAGE = 1;

	public CardTrick() {
		super(ID, info);
		setDamage(BASE_DAMAGE);
		setMagic(INCREASE_DAMAGE, UPGRADE_INCREASE_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
	}

	@Override
	public void applyPowers() {
		setDamage(BASE_DAMAGE + KidCard.totalFlipCount * magicNumber);

		super.applyPowers();

		initializeDescription();
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new CardTrick();
	}
}