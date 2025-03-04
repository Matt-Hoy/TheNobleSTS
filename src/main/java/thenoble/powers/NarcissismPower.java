package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class NarcissismPower extends BasePower {
  public static final String POWER_ID = makeID("Narcissism");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public NarcissismPower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void atStartOfTurn() {
    addToBot(
        new ApplyPowerAction(
            AbstractDungeon.player,
            AbstractDungeon.player,
            new DexterityPower(AbstractDungeon.player, amount)));
    addToBot(
        new ApplyPowerAction(
            AbstractDungeon.player,
            AbstractDungeon.player,
            new StrengthPower(AbstractDungeon.player, amount)));
  }

  public void updateDescription() {
    description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
  }
}
