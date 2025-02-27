package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import java.util.Objects;
import thenoble.cards.type.CachetCard;

public class ConfidencePower extends BasePower implements BetterOnApplyPowerPower {
  public static final String POWER_ID = makeID("Confidence");
  private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public ConfidencePower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public boolean betterOnApplyPower(
      AbstractPower power, AbstractCreature creature, AbstractCreature creature1) {
    if (Objects.equals(power.ID, ConfidencePower.POWER_ID)) {
      return true;
    }
    int confStacks = getConfStacks();
    if (confStacks > 0 && !Objects.equals(power.ID, ConfidencePower.POWER_ID)) {
      // important to note that this means you cannot use Confidence with powers that increase
      // strength by 0.
      if (power.amount == 0 && Objects.equals(power.ID, StrengthPower.POWER_ID)) {
        power.amount -= confStacks;
      } else if (power.amount >= 0) {
        power.amount += confStacks;
      } else {
        power.amount -= confStacks;
      }
      power.updateDescription();
      return true;
    }
    return false;
  }

  public static int getConfStacks() {
    if (AbstractDungeon.player == null) {
      return 0;
    }
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
    // && !Objects.equals(power.ID, ConfidencePower.POWER_ID)
    if (source == owner && !Objects.equals(power.ID, ConfidencePower.POWER_ID)) {
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
  public void onRemove() {
    for (AbstractCard card : AbstractDungeon.player.hand.group) {
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
    for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
    for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
  }

  @Override
  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
