package thenoble.util.model;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thenoble.powers.BasePower;

public class PowerTemplate extends BasePower {
  public static final String POWER_ID = makeID("PowerNameHere");
  private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public PowerTemplate(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
