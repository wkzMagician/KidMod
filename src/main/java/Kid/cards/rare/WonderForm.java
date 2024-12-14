package Kid.cards.rare;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.powers.WonderFormPower;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class WonderForm extends KidCard {
	public static final String ID = makeID(WonderForm.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.POWER,
			CardRarity.RARE,
			CardTarget.SELF,
			3
	);

	public WonderForm() {
		super(ID, info);

		if(this.upgraded){
			setEthereal(false);
		}else{
			setEthereal(true);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new WonderFormPower(p, -1)));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new WonderForm();
	}
}