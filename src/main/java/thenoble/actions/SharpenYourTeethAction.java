package thenoble.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;

public class SharpenYourTeethAction extends AbstractGameAction {
  private final AbstractPlayer player;
  private final int cardTarget;
  private final AbstractCard.CardType typeToCheck = AbstractCard.CardType.ATTACK;
  private int tracker = 0;

  public SharpenYourTeethAction(int cardTarget) {
    this.player = AbstractDungeon.player;
    this.cardTarget = cardTarget;
  }

  @Override
  public void update() {
    if (tracker >= cardTarget
        || (player.drawPile.isEmpty() && player.discardPile.isEmpty())
        || player.hand.size() >= 10) {
      isDone = true;
      return;
    }

    if (player.hasPower(NoDrawPower.POWER_ID)) {
      player.getPower(NoDrawPower.POWER_ID).flash();
      this.isDone = true;
      return;
    }

    if (!player.drawPile.isEmpty()) {
      AbstractCard card = player.drawPile.group.get(player.drawPile.size() - 1);
      if (card.type == typeToCheck) {
        tracker++;
      }
      addToBot(new DrawCardAction(1));
      addToBot(new SharpenYourTeethAction(cardTarget - tracker));
    } else {
      addToTop(new EmptyDeckShuffleAction());
      addToBot(new SharpenYourTeethAction(cardTarget));
    }

    isDone = true;
    tickDuration();
  }
}
