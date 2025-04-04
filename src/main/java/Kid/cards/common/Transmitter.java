package Kid.cards.common;

import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Transmitter extends KidCard {
	public static final String ID = makeID(Transmitter.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.COMMON,
			CardTarget.SELF,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:Transmitter");

	public Transmitter() {
		super(ID, info);

		this.isActual = true;

		setCostUpgrade(0);

		setExhaust(true);
		setSelfRetain(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		String text = cardStrings.EXTENDED_DESCRIPTION[0];
		// 选择一张手牌
		addToBot(new SelectCardsInHandAction(1, text, c -> c instanceof KidCard && !((KidCard) c).isMarked(), list -> {
			for (AbstractCard c : list) {
				// 设置高亮
//				c.glowColor = GOLD_BORDER_GLOW_COLOR;
				((KidCard)c).setMarked(true);
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Transmitter();
	}
}