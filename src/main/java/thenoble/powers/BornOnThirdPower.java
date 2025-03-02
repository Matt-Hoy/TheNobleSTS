package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BornOnThirdPower extends BasePower {
  public static final String POWER_ID = makeID("BornOnThird");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public BornOnThirdPower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void atStartOfTurn() {
    for (int i = 0; i < amount; i++) {
      AbstractCard card =
          AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.SKILL).makeCopy();
      addToBot(new MakeTempCardInHandAction(card));
    }
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
