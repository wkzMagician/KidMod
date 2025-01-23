package Kid.cards.rare;

import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Steal extends KidCard {
	public static final String ID = makeID(Steal.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.SKILL,
			CardRarity.RARE,
			CardTarget.SELF,
			0
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:Steal");

	public Steal() {
		super(ID, info);

		setExhaust(true);
	}

	@Override
	public void upgrade() {
		super.upgrade();

		setInnate(true);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// 选择抽牌堆中的1张宝石牌加入手牌
		addToBot(new SelectCardsAction(p.drawPile.group, 1, cardStrings.EXTENDED_DESCRIPTION[0],
				false,  c -> c instanceof GemCard, list -> {
			System.out.println(list.size());

			for (AbstractCard c : list) {
				// 将选择的宝石牌加入手牌
				p.hand.addToHand(c);
				p.drawPile.removeCard(c);
				// 触发宝石的效果
				((GemCard) c).addPower();
			}
		}
		));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new Steal();
	}
}