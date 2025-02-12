package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

public class FancyAct extends KidCard {
	public static final String ID = makeID(FancyAct.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY,
			2
	);



	public FancyAct() {
		super(ID, info);

		// 升级后cost变为2
		setCostUpgrade(1);

		// 打出后消耗
		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 选择一名敌人施加缓慢debuff
		addToBot(new ApplyPowerAction(m, p, new SlowPower(m, 0)));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new FancyAct();
	}
}