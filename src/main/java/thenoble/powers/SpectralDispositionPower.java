package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;
import thenoble.actions.RandomCardFromExhaustPileToHandAction;

public class SpectralDispositionPower extends BasePower {
  public static final String POWER_ID = makeID("SpectralDisposition");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public SpectralDispositionPower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void atStartOfTurn() {
    addToBot(new RandomCardFromExhaustPileToHandAction());
  }

  public void updateDescription() {
    description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
