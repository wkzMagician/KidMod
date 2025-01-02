package Kid.cards.common;

import Kid.actions.SetCardSideAction;
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
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PokerGun extends KidCard {
	public static final String ID = makeID(PokerGun.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:PokerGun");

	private static final int DAMAGE_PER_CARD = 5;
	private static final int UPGRADE_DAMAGE = 2;

	public PokerGun() {
		super(ID, info);

		setDamage(DAMAGE_PER_CARD, UPGRADE_DAMAGE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new SelectCardsInHandAction(10, cardStrings.EXTENDED_DESCRIPTION[0],
				true, true,
				c -> c instanceof KidCard && ((KidCard) c).isReverse(), list -> {
			final int DAMAGE = damage;

			// 多段伤害
			for (int i = 0; i < list.size(); i++) {
				addToBot(new DamageAction(m, new DamageInfo(p, DAMAGE, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			}

			// 将list中的卡牌丢弃
			list.forEach(c -> addToBot(new DiscardSpecificCardAction(c)));
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new PokerGun();
	}
}