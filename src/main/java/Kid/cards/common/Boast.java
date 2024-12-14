package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.cards.KidCard;
import Kid.cards.Status.FakeGem;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Boast extends KidCard {
	public static final String ID = makeID(Boast.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			1
	);

	private static final int DAMAGE = 12;
	private static final int UPG_DAMAGE = 17;


	public Boast() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.

		this.cardsToPreview = new FakeGem();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		if(this.isReverse()){
			FakeGem fakeGem = new FakeGem();
			addToBot(new MakeTempCardInDrawPileAction(fakeGem, 1, true, true));
		}
		this.flip();
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Boast();
	}

}