package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PatronPower extends BasePower {
  public static final String POWER_ID = makeID("Patron");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public PatronPower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void onUseCard(AbstractCard card, UseCardAction action) {
    if (card.type == AbstractCard.CardType.POWER) {
      for (int i = 0; i < amount; i++) {
        AbstractCard newCard =
            AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER).makeCopy();
        addToBot(new MakeTempCardInHandAction(newCard));
      }
    }
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
