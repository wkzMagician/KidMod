package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.DisguisePower;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Peek extends KidCard {
	public static final String ID = makeID(Peek.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY,
			1
	);

	public Peek() {
		super(ID, info);

		setMagic(2, 1);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		applyPowers();

		// 造成伤害
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));

		// 清除魅力
		// 如果有DisguisePower，不清除
		if(p.hasPower("Kid:DisguisePower")) {
			// 获取DisguisePower
			DisguisePower dis_power = (DisguisePower) p.getPower("Kid:DisguisePower");
			// 如果DisguisePower的amount大于0
			if(dis_power.amount > 0) {
				// 减少DisguisePower的amount
				dis_power.amount--;
				// 更新DisguisePower的描述
				dis_power.updateDescription();

				if(dis_power.amount <= 0) {
					addToBot(new RemoveSpecificPowerAction(p, p, dis_power));
				}
			}
		}else{
			// 如果没有DisguisePower，清除CharmPower
			if(p.hasPower("Kid:CharmPower")) {
				addToBot(new RemoveSpecificPowerAction(p, p, p.getPower("Kid:CharmPower")));
			}
		}
	}

	@Override
	public void applyPowers() {
		// 获得能力的魅力层数
		int charm = 0;
		AbstractPower power = AbstractDungeon.player.getPower("Kid:CharmPower");
		if(power != null){
			charm = power.amount;
			if(charm <= 0) charm = 0;
		}

		// 计算伤害
		this.baseDamage = this.magicNumber * charm;

		super.applyPowers();
		this.rawDescription = cardStrings.DESCRIPTION;
		this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + this.damage + cardStrings.EXTENDED_DESCRIPTION[1];
		initializeDescription();
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Peek();
	}
}