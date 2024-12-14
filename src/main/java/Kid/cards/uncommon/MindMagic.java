package Kid.cards.uncommon;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.MindMagicPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MindMagic extends KidCard {
	public static final String ID = makeID(MindMagic.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.POWER,
			CardRarity.UNCOMMON,
			CardTarget.SELF,
			1
	);

	private static final int BLOCK = 2;
	private static final int UPG_BLOCK = 1;

	public MindMagic() {
		super(ID, info);

		setMagic(BLOCK, UPG_BLOCK);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new MindMagicPower(p, magicNumber)));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new MindMagic();
	}

}