package Kid.cards.common;

import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Recapture extends KidCard {
	public static final String ID = makeID(Recapture.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:Recapture");

	private static final int DAMAGE = 9;
	private static final int UPG_DAMAGE = 1;

	// 将弃牌堆卡牌放回手牌
	private static final int RECALL_AMOUNT = 1;
	private static final int UPG_RECALL_AMOUNT = 1;

	public Recapture() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
		setMagic(RECALL_AMOUNT, UPG_RECALL_AMOUNT); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		String text = cardStrings.EXTENDED_DESCRIPTION[0];

		// 获取弃牌堆大小
		int discardPileSize = AbstractDungeon.player.discardPile.size();
		int amount = Math.min(discardPileSize, magicNumber);

		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		addToBot(new BetterDiscardPileToHandAction(magicNumber));
		addToBot(new SetCardSideAction(p, p, amount, Strategy.TOP, true));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Recapture();
	}
}