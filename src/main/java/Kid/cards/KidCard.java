package Kid.cards;

import static Kid.util.GeneralUtils.removePrefix;
import static Kid.util.TextureLoader.getCardTextureString;

import Kid.actions.TriggerFlipPowerAction;
import Kid.powers.CharmPower;
import Kid.powers.ElfLipsPower;
import Kid.powers.LoopholeDetectionPower;
import Kid.util.CardStats;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class KidCard extends BaseCard {

	private static final Logger log = LoggerFactory.getLogger(KidCard.class);
	// 新的属性：翻面
	protected boolean isFlipped = false;

	// 是否被标记？
	public boolean isMarked = false;

//	protected String actualDescription;
	protected String actualName;
//	protected Integer actualCost;

	public CardRarity actualRarity;

	// 真实的卡牌无法被翻面
	protected boolean isActual = false;

		// 翻牌策略
	public enum Strategy {
		SELECT,
		RANDOM,
		TOP,
	}

	// 当前的翻牌次数，静态变量
	public static int flipCount = 0;
	public static int totalFlipCount = 0;

		// 构造方法

	public KidCard(String id, CardStats stats) {
		super(id, stats);

//		actualDescription = rawDescription;
		actualName = name;
//		actualCost = cost;

		actualRarity = rarity;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		// TODO
	}

	// 新的方法：翻面
	public void flip() {
			setFlipped(!isFlipped);
	}

	public void setFlipped(boolean flipped) {
			if(isActual) return;

			if(isFlipped == flipped) return;

			isFlipped = flipped;

			// 如果翻为反面，将图片设置为默认图片
			if (isFlipped) {
				if(!isMarked) loadCardImage(getCardTextureString("default", type));
				else loadCardImage(getCardTextureString("marked", type));

//				// 将cost设为-2，表示这张卡不显示能量
//				actualCost = cost;
//				cost = -2;

				boolean hasRelic = AbstractDungeon.player.hasRelic("Kid:Monocle");
				boolean hasPower = AbstractDungeon.player.hasPower(LoopholeDetectionPower.POWER_ID);

				if(!isMarked && !(hasPower && this.rarity == CardRarity.BASIC)) {
					this.isSeen = false;

//				// 将卡片名字设为"???"
					name = "???";

					// 如果有ElfLipsPower，不会隐藏稀有度
					if(!hasRelic) {
						rarity = CardRarity.SPECIAL;
					}
				}

//
//				// 设置卡片描述为"???"
//				actualDescription = rawDescription;
//				rawDescription = "???";
//				initializeDescription();

			} else {
				// 如果翻为正面，将图片设置为正面图片
				loadCardImage(getCardTextureString(removePrefix(this.cardID), type));

//				// 将cost设为原本的cost
//				cost = actualCost;

				this.isSeen = true;

//				// 将卡片名字设为原本的名字
				name = actualName;

				rarity = actualRarity;
//
//				// 设置卡片描述为原本的描述
//				rawDescription = actualDescription;
//				initializeDescription();
			}

			triggerOnFlip();

			applyPowers();
	}

	public void setMarked(boolean marked) {
		this.isMarked = marked;
		if(this.isReverse()){
			boolean hasRelic = AbstractDungeon.player.hasRelic("Kid:Monocle");
			boolean hasPower = AbstractDungeon.player.hasPower(LoopholeDetectionPower.POWER_ID);
			if(!isMarked && !(hasPower && this.rarity == CardRarity.BASIC)) {
				this.isSeen = false;
				name = "???";
				// 如果有ElfLipsPower，不会隐藏稀有度
				if(!hasRelic) {
					rarity = CardRarity.SPECIAL;
				}
				loadCardImage(getCardTextureString("default", type));
			} else {
				loadCardImage(getCardTextureString("marked", type));
				this.isSeen = true;
				this.name = actualName;
				this.rarity = actualRarity;
			}
			applyPowers();
		}
		if(marked){
			this.glowColor = new Color(1.0F, 0.0F, 0.0F, 0.25F);
		}else{
			this.glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);
		}
	}

	public boolean isReverse() {
			return isFlipped;
	}

	public boolean isMarked() {
		return isMarked;
	}


	// 回合开始时
	@Override
	public void atTurnStart() {
		super.atTurnStart();

		KidCard.flipCount = 0;
	}

	// 重写makeStatEquivalentCopy方法，使得复制的卡牌也是翻面的
	@Override
	public KidCard makeStatEquivalentCopy() {
			KidCard card = (KidCard) super.makeStatEquivalentCopy();
			card.isActual = this.isActual;
			card.setFlipped(this.isFlipped);
			card.setMarked(this.isMarked);
			return card;
	}

	public void triggerOnFlip() {
		if(isActual) return;

		KidCard.flipCount++;
		KidCard.totalFlipCount++;

		addToTop(new TriggerFlipPowerAction(AbstractDungeon.player, AbstractDungeon.player));

		if(AbstractDungeon.player.hasRelic("Kid:Rose")){
			AbstractDungeon.player.getRelic("Kid:Rose").flash();
			addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CharmPower(AbstractDungeon.player, 1)));
		}
	}
}
