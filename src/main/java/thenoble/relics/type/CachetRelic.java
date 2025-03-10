package thenoble.relics.type;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thenoble.relics.BaseRelic;

public abstract class CachetRelic extends BaseRelic {
  public void increment(int cachetCount) {}

  public CachetRelic(
      String id, String imageName, AbstractCard.CardColor pool, RelicTier tier, LandingSound sfx) {
    super(id, imageName, pool, tier, sfx);
  }
}
