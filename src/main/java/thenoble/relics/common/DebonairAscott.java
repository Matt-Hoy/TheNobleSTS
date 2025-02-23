package thenoble.relics.common;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.powers.ConfidencePower;
import thenoble.relics.BaseRelic;

public class DebonairAscott extends BaseRelic {
  private static final String NAME = "DebonairAscott";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.COMMON;
  private static final LandingSound SOUND = LandingSound.CLINK;
  private static final int CONFIDENCE_AMOUNT = 1;

  public DebonairAscott() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
  }

  @Override
  public void atBattleStart() {
    flash();
    addToTop(
        new ApplyPowerAction(
            AbstractDungeon.player,
            AbstractDungeon.player,
            new ConfidencePower(AbstractDungeon.player, CONFIDENCE_AMOUNT)));
    addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
  }

  @Override
  public AbstractRelic makeCopy() {
    return new DebonairAscott();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0] + CONFIDENCE_AMOUNT + DESCRIPTIONS[1];
  }
}
