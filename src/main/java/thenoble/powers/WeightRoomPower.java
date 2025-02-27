package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class WeightRoomPower extends BasePower {
  public static final String POWER_ID = makeID("WeightRoom");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = true;

  public WeightRoomPower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void onUseCard(AbstractCard card, UseCardAction action) {
    if (card.type == AbstractCard.CardType.SKILL) {
      addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
    }
  }

  @Override
  public void atEndOfTurn(boolean isPlayer) {
    addToTop(new RemoveSpecificPowerAction(owner, owner, WeightRoomPower.POWER_ID));
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
