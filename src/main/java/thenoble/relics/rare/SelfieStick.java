package thenoble.relics.rare;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.relics.type.CachetRelic;

public class SelfieStick extends CachetRelic {
  private static final String NAME = "SelfieStick";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.RARE;
  private static final LandingSound SOUND = LandingSound.FLAT;
  private static final int STRENGTH_AMOUNT = 1;

  public SelfieStick() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    counter = 0;
  }

  @Override
  public void increment(int cachetCount) {
    for (int i = 0; i < cachetCount; i++) {
      counter++;
      if (counter % 3 == 0) {
        counter = 0;
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(
            new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, STRENGTH_AMOUNT)));
      } else if (counter == 2) {
        beginPulse();
      }
    }
  }

  @Override
  public AbstractRelic makeCopy() {
    return new SelfieStick();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0] + STRENGTH_AMOUNT + DESCRIPTIONS[1];
  }
}
