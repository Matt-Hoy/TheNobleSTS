package thenoble.cards.type;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.ArrayList;
import java.util.Objects;
import thenoble.cards.NobleCard;
import thenoble.powers.ConfidencePower;
import thenoble.relics.rare.WoodenDeckBox;
import thenoble.util.CardStats;

public abstract class CachetCard extends NobleCard {

  public CachetCard(String ID, CardStats info) {
    super(ID, info);
  }

  public void onConfidenceUpdated(AbstractPower power) {}

  public void onConfidenceRemoved() {}

  public int cachetAmount() {
    for (AbstractPower individualPower : AbstractDungeon.player.powers) {
      if (Objects.equals(individualPower.ID, ConfidencePower.POWER_ID)) {
        return individualPower.amount;
      }
    }
    return 0;
  }

  protected void onCachet() {
    int cachetTimes = cachetAmount();

    //    for (int i = 0; i < cachetTimes; i++) {
    //      // You can do stuff here if you need to.
    //    }
    if (AbstractDungeon.player.hasRelic(WoodenDeckBox.ID)) {
      addToBot(new DrawCardAction(AbstractDungeon.player, 1));
    }
  }

  /**
   * This method must be called by your cachet card in order to apply its effect.
   *
   * @param player you.
   * @param monster the monster targeted by the cachet effect (if applicable).
   * @param cachetTimes the number of times this effect should be applied.This method should be
   *     overridden by your cachet card in order to apply its effect.
   */
  public void triggerCachetEffect(AbstractPlayer player, AbstractMonster monster, int cachetTimes) {
    onCachet(); // Things that happen before cachet.

    for (int i = 0; i < cachetTimes; i++) {
      cachetEffect(player, monster);
      // Here's where you can trigger relics that care about cachet loops
    }

    addToBot(new RemoveSpecificPowerAction(player, player, ConfidencePower.POWER_ID));
  }

  /**
   * This method must be called by your cachet card in order to apply its effect.
   *
   * @param player you.
   * @param monsters the monsters targeted by the cachet effect (if applicable).
   * @param cachetTimes the number of times this effect should be applied.This method should be
   *     overridden by your cachet card in order to apply its effect.
   */
  public void triggerCachetEffect(
      AbstractPlayer player, ArrayList<AbstractMonster> monsters, int cachetTimes) {
    onCachet(); // Things that happen before cachet.

    for (int i = 0; i < cachetTimes; i++) {
      cachetEffect(player, monsters);
      // Here's where you can trigger relics that care about cachet
    }

    addToBot(new RemoveSpecificPowerAction(player, player, ConfidencePower.POWER_ID));
  }

  /**
   * This method can be overridden by your cachet card in order to describe its effect.
   *
   * @param player you.
   * @param monster the monster targeted by the cachet effect (if applicable).
   */
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {}

  /**
   * This method can be overridden by your cachet card in order to describe its effect.
   *
   * @param player you.
   * @param monsters The monsters targeted by the cachet effect.
   */
  public void cachetEffect(AbstractPlayer player, ArrayList<AbstractMonster> monsters) {}
}
