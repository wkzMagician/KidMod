package Kid.cards;

import static Kid.util.TextureLoader.getCardTextureString;

import Kid.actions.TriggerFlipPowerAction;
import Kid.util.CardStats;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class KidCard extends BaseCard {
	// 新的属性：翻面
	protected boolean isFlipped = false;

//	protected String actualDescription;
	protected String actualName;
//	protected Integer actualCost;

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

		// 构造方法

	public KidCard(String id, CardStats stats) {
		super(id, stats);

//		actualDescription = rawDescription;
		actualName = name;
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
				loadCardImage(getCardTextureString("default", type));

//				// 将cost设为-2，表示这张卡不显示能量
//				actualCost = cost;
//				cost = -2;

				this.isSeen = false;

//				// 将卡片名字设为"???"
				actualName = name;
				name = "???";
//
//				// 设置卡片描述为"???"
//				actualDescription = rawDescription;
//				rawDescription = "???";
//				initializeDescription();

			} else {
				// 如果翻为正面，将图片设置为正面图片
				loadCardImage(getCardTextureString(cardID, type));

//				// 将cost设为原本的cost
//				cost = actualCost;

				this.isSeen = true;

//				// 将卡片名字设为原本的名字
				name = actualName;
//
//				// 设置卡片描述为原本的描述
//				rawDescription = actualDescription;
//				initializeDescription();
			}

			triggerOnFlip();

			applyPowers();
	}

	public boolean isReverse() {
			return isFlipped;
	}


	// 回合开始时
	@Override
	public void atTurnStart() {
		super.atTurnStart();

		flipCount = 0;
	}







	// 重写makeStatEquivalentCopy方法，使得复制的卡牌也是翻面的
	@Override
	public KidCard makeStatEquivalentCopy() {
			KidCard card = (KidCard) super.makeStatEquivalentCopy();
			card.isFlipped = this.isFlipped;
			return card;
	}

	public void triggerOnFlip() {
		if(isActual) return;

		flipCount++;

		addToTop(new TriggerFlipPowerAction(AbstractDungeon.player, AbstractDungeon.player));
	}
}
