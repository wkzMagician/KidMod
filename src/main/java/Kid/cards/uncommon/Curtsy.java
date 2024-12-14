package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.CharmPower;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Curtsy extends KidCard {
	public static final String ID = makeID(Curtsy.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	public Curtsy() {
		super(ID, info);

		setExhaust(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 获得能力的魅力层数
		AbstractPower power = p.getPower("Kid:CharmPower");
		if(power == null) return;
		int charm = power.amount;
		if(charm <= 0) return;
		// 翻倍

		if(this.upgraded){ // 三倍
			charm *= 2;
		}

		addToBot(new ApplyPowerAction(p, p, new CharmPower(p, charm), charm));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Curtsy();
	}
}