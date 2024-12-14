package Kid.cards.common;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MysticTechniques extends KidCard {
	public static final String ID = makeID(MysticTechniques.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			2
	);

	private static final int ATTACK_DMG = 12;
	private static final int UPG_ATTACK_DMG = 4;

	public MysticTechniques() {
		super(ID, info);

		setDamage(ATTACK_DMG, UPG_ATTACK_DMG); //Sets the card's damage and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		if(this.isReverse()){
			addToBot(new GainEnergyAction(2));
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new MysticTechniques();
	}
}