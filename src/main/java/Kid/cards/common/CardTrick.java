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

	private static final int DAMAGE = 4;
	private static final int UPGRADE_DAMAGE = 2;

	public CardTrick() {
		super(ID, info);
		setDamage(DAMAGE, UPGRADE_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0; i < flipCount; i++) {
			addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
		}

		this.rawDescription = cardStrings.DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
		this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + flipCount;
		if(flipCount == 1) {
			this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
		}else {
			this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
		}
		initializeDescription();
	}

	@Override
	public void onMoveToDiscard() {
		this.rawDescription = cardStrings.DESCRIPTION;
		initializeDescription();
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new CardTrick();
	}
}