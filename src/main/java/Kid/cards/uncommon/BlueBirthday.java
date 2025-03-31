package Kid.cards.uncommon;

import Kid.cards.GemCard;
import Kid.character.Kid;
import Kid.powers.BloodTearsPower;
import Kid.powers.BlueBirthdayPower;
import Kid.powers.DarkStarPower;
import Kid.powers.PandoraPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BlueBirthday extends GemCard {
	public static final String ID = makeID(BlueBirthday.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			-2
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:BlueBirthday");

	private static final int ENERGY_NUM = 1;

	private static final int PLAY_NUM = 4;
	private static final int UP_PLAY_NUM = 2;

	private int cardPlayed = 0;

	public BlueBirthday() {
		super(ID, info);

		setMagic(PLAY_NUM, UP_PLAY_NUM);
	}

	@Override
	public void triggerOnOtherCardPlayed(AbstractCard c) {
		super.triggerOnOtherCardPlayed(c);

		cardPlayed++;

		if(cardPlayed >= this.magicNumber) {
			cardPlayed = 0;
			addToBot(new DiscardSpecificCardAction(this));
		}
	}

	@Override
	public void atTurnStart() {
		super.atTurnStart();

		cardPlayed = 0;
		resetAttributes();
		applyPowers();
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
		this.rawDescription = cardStrings.DESCRIPTION;
		this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + cardPlayed;
		if (cardPlayed == 1) {
			this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
		} else {
			this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
		}
		initializeDescription();
	}

	@Override
	public void addPower() {
		super.addPower();

		addToBot(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new BlueBirthdayPower(AbstractDungeon.player, ENERGY_NUM),
				ENERGY_NUM
		));
	}

	@Override
	public void removePower() {
		super.removePower();

		// 获得能力
		AbstractPower power = AbstractDungeon.player.getPower(BlueBirthdayPower.POWER_ID);
		if(power == null) return;

		int amount = power.amount;

		addToBot(new ReducePowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				BlueBirthdayPower.POWER_ID,
				1
		));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new BlueBirthday();
	}
}