package Kid.actions;

import Kid.KidMod;
import Kid.util.KidTutorial;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.io.IOException;

public class MessageCaller extends AbstractGameAction {
	private float startingDuration;

	public MessageCaller() {

		this.startingDuration = Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
	}


	public void update() {
		if (KidMod.unseenTutorial) {
			AbstractDungeon.ftue = new KidTutorial();
			KidMod.unseenTutorial = false;

			try {
				KidMod.saveTutorialSeen();
				this.isDone = true;
				;
			} catch (IOException e) {
				e.printStackTrace();
				this.isDone = true;
			}
		}
	}
}