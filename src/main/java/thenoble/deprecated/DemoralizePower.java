package thenoble.deprecated;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thenoble.powers.BasePower;

public class DemoralizePower extends BasePower {
  public static final String POWER_ID = makeID("Demoralize");
  private static final PowerType TYPE = PowerType.DEBUFF;
  private static final boolean TURN_BASED = true;

  public DemoralizePower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  // TODO This does not work.
  @Override
  public void wasHPLost(DamageInfo info, int damageAmount) {
    if (info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0) {
      flash();
      addToBot(
          new ApplyPowerAction(
              AbstractDungeon.player,
              AbstractDungeon.player,
              new StrengthPower(AbstractDungeon.player, amount)));
      addToBot(
          new ApplyPowerAction(
              AbstractDungeon.player,
              AbstractDungeon.player,
              new LoseStrengthPower(AbstractDungeon.player, amount)));
      addToBot(
          new ApplyPowerAction(
              owner, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -amount)));
      addToBot(
          new ApplyPowerAction(
              owner,
              AbstractDungeon.player,
              new GainStrengthPower(AbstractDungeon.player, amount)));
      addToBot(
          new RemoveSpecificPowerAction(owner, AbstractDungeon.player, DemoralizePower.POWER_ID));
    }
  }

  @Override
  public void atEndOfRound() {
    if (owner.hasPower(DemoralizePower.POWER_ID)) {
      addToBot(
          new RemoveSpecificPowerAction(owner, AbstractDungeon.player, DemoralizePower.POWER_ID));
    }
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
  }
}
