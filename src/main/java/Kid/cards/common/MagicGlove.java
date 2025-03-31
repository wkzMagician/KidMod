package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagicGlove extends KidCard {
	public static final String ID = makeID(MagicGlove.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			1
	);

	private static final int DAMAGE = 8;
	private static final int UPG_DAMAGE = 3;

	private static final int FLIP_AMOUNT = 1;
	private static final int UPG_FLIP_AMOUNT = 0;

	public MagicGlove() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
		setMagic(FLIP_AMOUNT, UPG_FLIP_AMOUNT); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		addToBot(new DrawCardAction(p, 1));
		addToBot(new SelectCardsInHandAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0],
				true, true, card -> card instanceof KidCard, list -> {
			for (AbstractCard c : list) {
				((KidCard) c).flip();
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new MagicGlove();
	}
}