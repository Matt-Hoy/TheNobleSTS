package thenoble.relics.shop;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.relics.BaseRelic;

public class SpectralLens extends BaseRelic {
  private static final String NAME = "SpectralLens";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.COMMON;
  private static final LandingSound SOUND = LandingSound.CLINK;

  public SpectralLens() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
  }

  @Override
  public AbstractRelic makeCopy() {
    return new SpectralLens();
  }

  @Override
  public void onExhaust(AbstractCard card) {
    addToBot(new DrawCardAction(AbstractDungeon.player, 1));
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }
}
