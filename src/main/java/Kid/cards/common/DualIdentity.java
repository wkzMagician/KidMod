package Kid.cards.common;

import static Kid.util.TextureLoader.getCardTextureString;

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
import com.megacrit.cardcrawl.core.CardCrawlGame;
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

	private static final int DAMAGE = 11;
	private static final int UPG_DAMAGE = 3;

	private static final int BLOCK = 9;
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
		flip();
	}

	@Override
	public void setFlipped(boolean flipped) {
		super.setFlipped(flipped);
		this.isSeen = true;
		this.name = this.actualName;
		this.rarity = this.actualRarity;

		if(!isFlipped){
			this.type = CardType.ATTACK;
			this.target = CardTarget.ENEMY;
			this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
		}else{
			this.type = CardType.SKILL;
			this.target = CardTarget.SELF;
			this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
		}

		initializeDescription();
		loadCardImage(getCardTextureString(cardID, type));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new DualIdentity();
	}

}