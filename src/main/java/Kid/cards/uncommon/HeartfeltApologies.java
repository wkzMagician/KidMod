package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HeartfeltApologies extends KidCard {
	public static final String ID = makeID(HeartfeltApologies.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:HeartfeltApologies");

	private static final int DAMAGE = 15;
	private static final int UPG_DAMAGE = 5;

	public HeartfeltApologies() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
			addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		// 如果手牌都是正面
		for(AbstractCard c : p.hand.group) {
			if(c instanceof KidCard && ((KidCard) c).isReverse()) {
				this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
				return false;
			}
		}

		return super.canUse(p, m);
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new HeartfeltApologies();
	}
}