package thenoble.relics.uncommon;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.relics.BaseRelic;

public class ClayBattery extends BaseRelic {
  private static final String NAME = "ClayBattery";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.UNCOMMON;
  private static final LandingSound SOUND = LandingSound.FLAT;
  private static final int ENERGY_AMOUNT = 1;

  public ClayBattery() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    counter = 0;
  }

  @Override
  public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
    if (targetCard.type == AbstractCard.CardType.POWER) {
      counter++;
      if (counter % 10 == 0) {
        counter = 0;
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new GainEnergyAction(ENERGY_AMOUNT));
      }
    }
  }

  @Override
  public AbstractRelic makeCopy() {
    return new ClayBattery();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }
}
