package thenoble.relics.rare;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.relics.type.CachetRelic;

public class DanceCertificate extends CachetRelic {
  private static final String NAME = "DanceCertificate";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.RARE;
  private static final LandingSound SOUND = LandingSound.FLAT;
  private static final int DEXTERITY_AMOUNT = 1;

  public DanceCertificate() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    counter = 0;
  }

  @Override
  public void increment(int cachetCount) {
    for (int i = 0; i < cachetCount; i++) {
      counter++;
      if (counter % 2 == 0) {
        counter = 0;
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(
            new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new DexterityPower(AbstractDungeon.player, DEXTERITY_AMOUNT)));
      } else if (counter == 1) {
        beginPulse();
      }
    }
  }

  @Override
  public AbstractRelic makeCopy() {
    return new DanceCertificate();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0] + DEXTERITY_AMOUNT + DESCRIPTIONS[1];
  }
}
