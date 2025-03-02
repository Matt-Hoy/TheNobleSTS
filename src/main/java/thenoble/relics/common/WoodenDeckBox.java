package thenoble.relics.common;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.relics.BaseRelic;

public class WoodenDeckBox extends BaseRelic {
  private static final String NAME = "WoodenDeckBox";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.COMMON;
  private static final LandingSound SOUND = LandingSound.FLAT;

  public WoodenDeckBox() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
  }

  @Override
  public AbstractRelic makeCopy() {
    return new WoodenDeckBox();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }
}
