package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Flashbang extends KidCard {
	public static final String ID = makeID(Flashbang.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	public Flashbang() {
		super(ID, info);
		setExhaust(true);
	}

	@Override
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			setExhaust(false);
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 遍历手牌
		for (AbstractCard c : p.hand.group) {
			// 如果是KidCard
			if (c instanceof KidCard) {
				((KidCard) c).setFlipped(false);
			}
		}
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Flashbang();
	}
}