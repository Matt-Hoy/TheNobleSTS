package thenoble.cards.type;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import thenoble.cards.NobleCard;
import thenoble.powers.ConfidencePower;
import thenoble.relics.common.WoodenDeckBox;
import thenoble.relics.rare.DanceCertificate;
import thenoble.relics.rare.SelfieStick;
import thenoble.relics.type.CachetRelic;
import thenoble.util.CardStats;

public abstract class CachetCard extends NobleCard {
  private static final ArrayList<String> CACHET_RELICS =
      new ArrayList<>(Arrays.asList(SelfieStick.ID, DanceCertificate.ID));

  public CachetCard(String ID, CardStats info) {
    super(ID, info);
  }

  public void onConfidenceUpdated(AbstractPower power) {}

  public void onConfidenceRemoved() {}

  public static int cachetAmount() {
    if (AbstractDungeon.player == null) {
      return 0;
    }
    for (AbstractPower individualPower : AbstractDungeon.player.powers) {
      if (Objects.equals(individualPower.ID, ConfidencePower.POWER_ID)) {
        return individualPower.amount;
      }
    }
    return 0;
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
    //    onCachet(); // Things that happen before cachet.

    int cachetCount = cachetAmount();

    for (int i = 0; i < cachetTimes; i++) {
      cachetEffect(player, monster);
    }

    addToBot(new RemoveSpecificPowerAction(player, player, ConfidencePower.POWER_ID));

    if (AbstractDungeon.player.hasRelic(WoodenDeckBox.ID)) {
      addToBot(new DrawCardAction(AbstractDungeon.player, 1));
    }

    for (AbstractRelic abstractRelic : AbstractDungeon.player.relics) {
      if (CACHET_RELICS.contains(abstractRelic.relicId)) {
        CachetRelic relic = (CachetRelic) abstractRelic;
        relic.increment(cachetCount);
      }
    }
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
    //    onCachet(); // Things that happen before cachet.

    int cachetCount = cachetAmount();

    for (int i = 0; i < cachetTimes; i++) {
      cachetEffect(player, monsters);
    }

    addToBot(new RemoveSpecificPowerAction(player, player, ConfidencePower.POWER_ID));

    if (AbstractDungeon.player.hasRelic(WoodenDeckBox.ID)) {
      addToBot(new DrawCardAction(AbstractDungeon.player, 1));
    }

    for (AbstractRelic abstractRelic : AbstractDungeon.player.relics) {
      if (Objects.equals(abstractRelic.relicId, SelfieStick.ID)) {
        CachetRelic relic = (CachetRelic) abstractRelic;
        relic.increment(cachetCount);
      }
    }
  }

  //  protected void onCachet() {
  //    int cachetTimes = cachetAmount();
  //
  //        for (int i = 0; i < cachetTimes; i++) {
  //          // You can do stuff here if you need to.
  //        }
  //  }

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
