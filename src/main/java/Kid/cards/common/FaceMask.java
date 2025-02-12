package Kid.cards.common;

import Kid.actions.FlipCardAction;
import Kid.actions.SetCardSideAction;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FaceMask extends KidCard {
	public static final String ID = makeID(FaceMask.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:FaceMask");

	private static final int BLOCK = 8;
	private static final int UPG_BLOCK = 2;

	private static final int FLIP = 2;
	private static final int UPG_FLIP = 1;

	public FaceMask() {
		super(ID, info);

		this.isActual = true;

		setBlock(BLOCK, UPG_BLOCK); //Sets the card's block and how much it changes when upgraded.
		setMagic(FLIP, UPG_FLIP); //Sets the card's magic number and how much it changes when upgraded.
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, this.block));
		addToBot(new SelectCardsInHandAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0],
				true, true, c -> c instanceof KidCard, list -> {
			for(AbstractCard c : list) {
				((KidCard) c).flip();
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new FaceMask();
	}
}