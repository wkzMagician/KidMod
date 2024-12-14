package Kid.cards.rare;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.powers.BloodTearsPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class TowerDiamond extends GemCard {
	public static final String ID = makeID(TowerDiamond.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			-2
	);

	private static final int STRENGTH = 1;
	private static final int UPG_STRENGTH = 1;

	public TowerDiamond() {
		super(ID, info);

		setSelfRetain(false);

		setMagic(STRENGTH, UPG_STRENGTH);
	}

	@Override
	public void onRetained() {
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new TowerDiamond();
	}
}