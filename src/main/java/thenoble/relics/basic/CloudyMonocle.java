package thenoble.relics.basic;

import static thenoble.TheNoble.makeID;
import static thenoble.powers.ConfidencePower.getConfStacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.powers.ConfidencePower;
import thenoble.relics.BaseRelic;

public class CloudyMonocle extends BaseRelic {
  private static final String NAME = "CloudyMonocle";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.STARTER;
  private static final LandingSound SOUND = LandingSound.CLINK;
  private static final int CONFIDENCE_AMOUNT = 1;

  @Override
  public void atTurnStart() {
    counter = 0;
  }

  public CloudyMonocle() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
  }

  @Override
  public void onUseCard(AbstractCard card, UseCardAction useCardAction) {
    if (card.type == AbstractCard.CardType.SKILL) {
      ++counter;
      if (counter % 4 == 0) {
        counter = 0;
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(
            new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                // This is the logic to prevent confidence from stacking with itself.
                new ConfidencePower(AbstractDungeon.player, CONFIDENCE_AMOUNT)));
      } else if (counter == 3) {
        beginPulse();
      }
    }
  }

  @Override
  public void onVictory() {
    counter = -1;
  }

  @Override
  public AbstractRelic makeCopy() {
    return new CloudyMonocle();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0] + CONFIDENCE_AMOUNT + DESCRIPTIONS[1];
  }
}
