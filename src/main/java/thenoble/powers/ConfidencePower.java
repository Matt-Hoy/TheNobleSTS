package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import thenoble.cards.type.CachetCard;

public class ConfidencePower extends BasePower implements OnCreateCardInterface {
  public static final String POWER_ID = makeID("Confidence");
  private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public ConfidencePower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
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
  public void onInitialApplication() {
    modMagicNumbers();
  }

  @Override
  public void stackPower(int stackAmount) {
    modMagicNumbers();
    this.amount += stackAmount;
  }

  @Override
  public void onRemove() {
    int stacks = getConfStacks();
    for (AbstractCard card : AbstractDungeon.player.hand.group) {
      if (usesMagic(card)) {
        card.magicNumber -= stacks;
        card.isMagicNumberModified = false;
      }
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
    for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
      if (usesMagic(card)) {
        card.magicNumber -= stacks;
        card.isMagicNumberModified = false;
      }
      if (card instanceof CachetCard) {
        ((CachetCard) card).onConfidenceRemoved();
      }
    }
    for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
      if (usesMagic(card)) {
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
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }

  private static void modMagicNumbers() {
    for (AbstractCard card : AbstractDungeon.player.hand.group) {
      if (usesMagic(card)) {
        card.magicNumber++;
        card.isMagicNumberModified = true;
      }
    }
    for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
      if (usesMagic(card)) {
        card.magicNumber++;
        card.isMagicNumberModified = true;
      }
    }
    for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
      if (usesMagic(card)) {
        card.magicNumber++;
        card.isMagicNumberModified = true;
      }
    }
  }

  private static boolean usesMagic(AbstractCard card) {
    return (card.baseMagicNumber > 0 || StringUtils.containsIgnoreCase(card.rawDescription, "!M!"));
  }

  @Override
  public void onCreateCard(AbstractCard card) {
    if (usesMagic(card)) {
      card.magicNumber += getConfStacks();
      card.isMagicNumberModified = true;
    }
  }
}
