package Kid.cards.common;

import static Kid.util.GeneralUtils.removePrefix;
import static Kid.util.TextureLoader.getCardTextureString;

import Kid.actions.FlipSpecificCardAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.ElfLipsPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class DualIdentity extends KidCard {
	public static final String ID = makeID(DualIdentity.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.COMMON,
			CardTarget.ENEMY,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:DualIdentity");

	private static final int DAMAGE = 10;
	private static final int UPG_DAMAGE = 3;

	private static final int BLOCK = 8;
	private static final int UPG_BLOCK = 3;

	public DualIdentity() {
		super(ID, info);

		setDamage(DAMAGE, UPG_DAMAGE);
		setBlock(BLOCK, UPG_BLOCK);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(!this.isReverse()){
			addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		}else{
			addToBot(new GainBlockAction(p, p, this.block));
		}

		// 翻面！
		addToBot(new FlipSpecificCardAction(this));
	}

	@Override
	public void setFlipped(boolean flipped) {
		if(isActual) return;
		if(isFlipped == flipped) return;

		isFlipped = flipped;

		if (isFlipped) {
			this.type = CardType.SKILL;
			this.target = CardTarget.SELF;
			this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];

			loadCardImage(getCardTextureString("default", type));
		} else {
			this.type = CardType.ATTACK;
			this.target = CardTarget.ENEMY;
			this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];

			loadCardImage(getCardTextureString(removePrefix(this.cardID), type));
		}

		triggerOnFlip();
		initializeDescription();
		applyPowers();
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new DualIdentity();
	}

}