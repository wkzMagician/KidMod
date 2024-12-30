package Kid.cards.rare;

import Kid.actions.RemoveBuffsAction;
import Kid.cards.GemCard;
import Kid.cards.KidCard;
import Kid.character.Kid;
import Kid.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SilverBullet extends KidCard {
	public static final String ID = makeID(SilverBullet.class.getSimpleName());
	private static final CardStats info = new CardStats(
			Kid.Meta.CARD_COLOR,
			CardType.ATTACK,
			CardRarity.RARE,
			CardTarget.ENEMY,
			1
	);

	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Kid:SilverBullet");

	public SilverBullet() {
		super(ID, info);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0],
				false, false, c -> c instanceof GemCard, list -> {
			for(AbstractCard c : list) {
				int damage = 0;
				if(this.upgraded){
					switch (c.rarity) {
						case COMMON:
							damage = 20;
							break;
						case UNCOMMON:
							damage = 30;
							break;
						case RARE:
							damage = 40;
							break;
					}
				}else{
					switch (c.rarity) {
						case COMMON:
							damage = 10;
							break;
						case UNCOMMON:
							damage = 20;
							break;
						case RARE:
							damage = 30;
							break;
					}
				}
				addToBot(new DamageAction(m, new DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
				addToBot(new ExhaustSpecificCardAction(c, p.hand));
			}
		}));
	}

	@Override
	public AbstractCard makeCopy() { //Optional
		return new SilverBullet();
	}
}