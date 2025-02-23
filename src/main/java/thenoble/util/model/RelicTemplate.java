package thenoble.util.model;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.relics.BaseRelic;

public class RelicTemplate extends BaseRelic {
  private static final String NAME = "RelicNameHere";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.COMMON;
  private static final LandingSound SOUND = LandingSound.CLINK;

  public RelicTemplate() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
  }

  @Override
  public AbstractRelic makeCopy() {
    return new RelicTemplate();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0] + DESCRIPTIONS[1];
  }
}
