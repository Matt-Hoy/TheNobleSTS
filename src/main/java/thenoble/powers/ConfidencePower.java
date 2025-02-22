package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.Objects;

public class ConfidencePower extends BasePower implements BetterOnApplyPowerPower {
  public static final String POWER_ID = makeID("Confidence");
  private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public ConfidencePower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  // TODO: Doesn't fucking work with strength. Figure out another strength pattern?
  @Override
  public boolean betterOnApplyPower(
      AbstractPower power, AbstractCreature creature, AbstractCreature creature1) {
    //    return (AbstractDungeon.player.hasPower(ConfidencePower.POWER_ID));
    int stackAmount = getConfStacks();
    if (AbstractDungeon.player.hasPower(ConfidencePower.POWER_ID)) {
      if (Objects.equals(power.ID, StrengthPower.POWER_ID) && stackAmount == 0) {
        power.amount -= getConfStacks();
      } else if (stackAmount >= 0 || Objects.equals(power.ID, ConfidencePower.POWER_ID)) {
        power.amount += stackAmount;
      } else {
        power.amount -= stackAmount;
      }
      power.amount += getConfStacks();
      power.updateDescription();
      return true;
    }
    return false;
  }

  public static int getConfStacks() {
    int confStacks = 0;
    for (AbstractPower power : AbstractDungeon.player.powers) {
      if (Objects.equals(power.ID, ConfidencePower.POWER_ID)) {
        confStacks = power.amount;
      }
    }
    return confStacks;
  }

  @Override
  public int betterOnApplyPowerStacks(
      AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
    if (source == owner) {
      //      Never gain 0 strength as it will subtract.
      if (Objects.equals(power.ID, StrengthPower.POWER_ID) && stackAmount == 0) {
        return stackAmount - getConfStacks();
      } else if (stackAmount >= 0 || Objects.equals(power.ID, ConfidencePower.POWER_ID)) {
        return stackAmount + getConfStacks();
      } else {
        return stackAmount - getConfStacks();
      }
    }
    return stackAmount;
  }

  @Override
  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
