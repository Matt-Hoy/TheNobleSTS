package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShoppingSpreePower extends BasePower {
  public static final String POWER_ID = makeID("ShoppingSpree");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public ShoppingSpreePower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void onUseCard(AbstractCard card, UseCardAction action) {
    if (card.type == AbstractCard.CardType.POWER) {
      addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, amount));
    }
  }

  public void updateDescription() {
    description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
