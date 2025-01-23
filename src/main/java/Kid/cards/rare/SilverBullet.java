package Kid.cards.rare;

import Kid.actions.RemoveBuffsAction;
import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SilverBullet extends KidCard {
	public static final String ID = makeID(SilverBullet.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.RARE,
			CardTarget.ENEMY,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:SilverBullet");

	private static final int DAMAGE_PER_CARD = 25;
	private static final int UPGRADE_DAMAGE = 10;

	public SilverBullet() {
		super(ID, info);

		setDamage(DAMAGE_PER_CARD, UPGRADE_DAMAGE);
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		// 手牌中是否有宝石？
		for(AbstractCard c : p.hand.group){
			if(c instanceof GemCard) return true;
		}

		this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
		return false;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0],
				false, false, c -> c instanceof GemCard, list -> {
			for(AbstractCard c : list) {
				addToBot(new DamageAction(m, new DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
				addToBot(new ExhaustSpecificCardAction(c, p.hand));
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new SilverBullet();
	}
}