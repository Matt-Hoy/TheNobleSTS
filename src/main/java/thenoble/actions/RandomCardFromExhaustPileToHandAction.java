package thenoble.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RandomCardFromExhaustPileToHandAction extends AbstractGameAction {
  private final AbstractPlayer player;

  public RandomCardFromExhaustPileToHandAction() {
    this.player = AbstractDungeon.player;
    setValues(player, player, amount);
    actionType = ActionType.CARD_MANIPULATION;
  }

  public void update() {
    if (!player.exhaustPile.isEmpty()) {
      AbstractCard card = player.exhaustPile.getRandomCard(AbstractDungeon.cardRandomRng);
      player.hand.addToHand(card.makeCopy());
      player.exhaustPile.removeCard(card);
      player.hand.refreshHandLayout();
    }

    tickDuration();
    isDone = true;
  }
}
