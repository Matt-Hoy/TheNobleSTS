package thenoble.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static thenoble.cards.type.CachetCard.cachetAmount;
import static thenoble.powers.ConfidencePower.usesMagic;

public class RandomCardFromExhaustPileToHandAction extends AbstractGameAction {
  private final AbstractPlayer player;

  public RandomCardFromExhaustPileToHandAction() {
    this.player = AbstractDungeon.player;
    setValues(player, player, amount);
    actionType = ActionType.CARD_MANIPULATION;
  }

  public void update() {
    if (exhaustIsValid()) {
      ArrayList<AbstractCard> candidates = new ArrayList<>();
      candidates.addAll(player.exhaustPile.getAttacks().group);
      candidates.addAll(player.exhaustPile.getSkills().group);
      candidates.addAll(player.exhaustPile.getPowers().group);
      int rand = AbstractDungeon.cardRandomRng.random(0, candidates.size() - 1);
      AbstractCard card = candidates.get(rand);
      AbstractCard newCard = card.makeCopy();
      player.hand.addToHand(newCard);
      if (cachetAmount() > 0) {
        if (usesMagic(card) && !newCard.isMagicNumberModified) {
          newCard.magicNumber += cachetAmount();
          newCard.isMagicNumberModified = true;
        }
      }
      player.exhaustPile.removeCard(card);
      player.hand.refreshHandLayout();
    }

    tickDuration();
    isDone = true;
  }

  private boolean exhaustIsValid() {
    return (!player.exhaustPile.isEmpty()
        && (!player.exhaustPile.getSkills().isEmpty()
            || !player.exhaustPile.getAttacks().isEmpty()
            || !player.exhaustPile.getPowers().isEmpty()));
  }
}
