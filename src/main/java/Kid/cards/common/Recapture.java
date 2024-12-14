package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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
		// 是否升级？
		String text = this.upgraded ? "选择一张卡牌放回手牌" : "选择两张卡牌放回手牌";

		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		addToBot(new SelectCardsAction(p.discardPile.group, magicNumber, text, false, c -> true, list -> {
			for (AbstractCard c : list) {
				if(c instanceof KidCard) {
					((KidCard) c).setFlipped(true);
				}

				p.hand.addToTop(c);
				p.discardPile.removeCard(c);
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Recapture();
	}
}