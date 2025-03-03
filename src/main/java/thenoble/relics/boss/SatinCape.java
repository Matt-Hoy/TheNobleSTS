package thenoble.relics.boss;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.relics.BaseRelic;

public class SatinCape extends BaseRelic {
  private static final String NAME = "SatinCape";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.COMMON;
  private static final LandingSound SOUND = LandingSound.CLINK;
  private static final int ENERGY_AMOUNT = 1;

  public SatinCape() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
  }

  @Override
  public AbstractRelic makeCopy() {
    return new SatinCape();
  }

  @Override
  public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
    if (targetCard.type == AbstractCard.CardType.POWER && targetCard.cost >= 2) {
      addToBot(new GainEnergyAction(ENERGY_AMOUNT));
    }
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }
}
