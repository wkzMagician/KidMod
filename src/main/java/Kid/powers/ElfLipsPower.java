package Kid.powers;
import Kid.cards.KidCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import java.util.ArrayList;
import java.util.Comparator;

public class ElfLipsPower extends BasePower {
		public static final String POWER_ID = "Kid:ElfLipsPower";

		public ElfLipsPower(AbstractCreature owner, int amount) {
				super(POWER_ID, PowerType.BUFF, false, owner, amount);
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0];
		}

		@Override
		public void onCardDraw(AbstractCard card) {
			// 如果卡大于等于两费，则标记之
			if(card.cost >= 2 && card instanceof KidCard) {
				((KidCard) card).setMarked(true);
			}
		}
}
