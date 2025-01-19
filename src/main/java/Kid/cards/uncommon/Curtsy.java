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
		setMagic(2, 1);
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
		this.rawDescription = cardStrings.DESCRIPTION;

		int count = 0;
		for(AbstractCard c : AbstractDungeon.player.hand.group) {
			if(c == this) continue;
			if(c instanceof KidCard && ((KidCard) c).isReverse()) {
				count++;
			}
		}

		if(count == 1) {
			this.rawDescription +=
					cardStrings.EXTENDED_DESCRIPTION[0] + count + cardStrings.EXTENDED_DESCRIPTION[1];
//		}else if(count > 1) {
		}else{
			this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count + cardStrings.EXTENDED_DESCRIPTION[2];
		}

		initializeDescription();
	}

	@Override
	public void onMoveToDiscard() {
		this.rawDescription = cardStrings.DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		int count = 0;
		for(AbstractCard c : p.hand.group) {
			if(c == this) continue;
			if(c instanceof KidCard && ((KidCard) c).isReverse()) {
				count++;
			}
		}

		int charm = count * magicNumber;

		addToBot(new ApplyPowerAction(p, p, new CharmPower(p, charm), charm));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Curtsy();
	}
}