package Kid.powers;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import java.util.ArrayList;

public class ElfLipsPower extends BasePower {
		public static final String POWER_ID = "Kid:ElfLipsPower";

		public ElfLipsPower(AbstractCreature owner) {
				super(POWER_ID, PowerType.BUFF, false, owner, -1);
		}

		@Override
		public void updateDescription() {
			this.description = DESCRIPTIONS[0];
		}
}
