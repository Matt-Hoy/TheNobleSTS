package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EntitlementPower extends BasePower {
  public static final String POWER_ID = makeID("Entitlement");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public EntitlementPower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void atStartOfTurnPostDraw() {
    addToBot(new DrawCardAction(AbstractDungeon.player, amount));
  }

  public void updateDescription() {
    description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
