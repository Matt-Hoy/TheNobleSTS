package thenoble.powers;

import static thenoble.TheNoble.makeID;
import static thenoble.cards.type.CachetCard.cachetAmount;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.commons.lang3.StringUtils;
import thenoble.cards.type.CachetCard;

public class ConfidencePower extends BasePower implements OnCreateCardInterface {
  public static final String POWER_ID = makeID("Confidence");
  private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public ConfidencePower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void onInitialApplication() {
    modMagicNumbers();
  }

  @Override
  public void stackPower(int stackAmount) {
    if (cachetAmount() < 1) {
      modMagicNumbers();
      this.amount += stackAmount;
    } else {
      addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
    }
  }

  @Override
  public void onRemove() {
    int stacks = cachetAmount();
    for (AbstractCard card : AbstractDungeon.player.hand.group) {
      if (usesMagic(card) && card.isMagicNumberModified) {
        card.magicNumber -= stacks;
        card.isMagicNumberModified = false;
      }
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
    for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
      if (usesMagic(card) && card.isMagicNumberModified) {
        card.magicNumber -= stacks;
        card.isMagicNumberModified = false;
      }
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
    for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
      if (usesMagic(card) && card.isMagicNumberModified) {
        card.magicNumber -= stacks;
        card.isMagicNumberModified = false;
      }
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
    for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
      if (usesMagic(card) && card.isMagicNumberModified) {
        card.magicNumber -= stacks;
        card.isMagicNumberModified = false;
      }
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
  }

  @Override
  public void updateDescription() {
    description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }

  private static void modMagicNumbers() {
    for (AbstractCard card : AbstractDungeon.player.hand.group) {
      if (usesMagic(card)) {
        card.magicNumber++;
        card.isMagicNumberModified = true;
      }
    }
    for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
      if (usesMagic(card)) {
        card.magicNumber++;
        card.isMagicNumberModified = true;
      }
    }
  }

  public static boolean usesMagic(AbstractCard card) {
    return (card.baseMagicNumber >= 0
        || StringUtils.containsIgnoreCase(card.rawDescription, "!M!"));
  }

  @Override
  public void onCardDraw(AbstractCard card) {
    modSpecificCard(card);
  }

  @Override
  public void onCreateCard(AbstractCard card) {
    modSpecificCard(card);
  }

  public static void modSpecificCard(AbstractCard card) {
    if (usesMagic(card) && !card.isMagicNumberModified) {
      card.magicNumber += cachetAmount();
      card.isMagicNumberModified = true;
    }
  }
}
