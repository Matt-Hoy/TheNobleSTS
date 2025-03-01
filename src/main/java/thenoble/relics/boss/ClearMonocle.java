package thenoble.relics.boss;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import java.util.Objects;
import thenoble.character.MyCharacter;
import thenoble.powers.ConfidencePower;
import thenoble.relics.BaseRelic;
import thenoble.relics.basic.CloudyMonocle;

public class ClearMonocle extends BaseRelic {
  private static final String NAME = "ClearMonocle";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.BOSS;
  private static final LandingSound SOUND = LandingSound.CLINK;
  private static final int CONFIDENCE_AMOUNT = 1;

  public ClearMonocle() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
  }

  @Override
  public void atTurnStart() {
    counter = 0;
  }

  @Override
  public void onUseCard(AbstractCard card, UseCardAction useCardAction) {
    if (card.type == AbstractCard.CardType.SKILL) {
      ++counter;
      if (counter % 3 == 0) {
        counter = 0;
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(
            new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new ConfidencePower(AbstractDungeon.player, CONFIDENCE_AMOUNT)));
      } else if (counter == 2) {
        beginPulse();
      }
    }
  }

  @Override
  public void onVictory() {
    counter = -1;
  }

  @Override
  public boolean canSpawn() {
    return AbstractDungeon.player.hasRelic(CloudyMonocle.ID);
  }

  @Override
  public void obtain() {
    if (AbstractDungeon.player.hasRelic(CloudyMonocle.ID)) {
      for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
        if (Objects.equals(AbstractDungeon.player.relics.get(i).relicId, CloudyMonocle.ID)) {
          instantObtain(AbstractDungeon.player, i, true);
          break;
        }
      }
    } else {
      super.obtain();
    }
  }

  @Override
  public AbstractRelic makeCopy() {
    return new ClearMonocle();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0] + CONFIDENCE_AMOUNT + DESCRIPTIONS[1];
  }
}
