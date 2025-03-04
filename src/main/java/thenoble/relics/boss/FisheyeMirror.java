package thenoble.relics.boss;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thenoble.character.MyCharacter;
import thenoble.relics.BaseRelic;

public class FisheyeMirror extends BaseRelic {
  private static final String NAME = "FisheyeMirror";
  public static final String ID = makeID(NAME);
  private static final RelicTier RARITY = RelicTier.BOSS;
  private static final LandingSound SOUND = LandingSound.MAGICAL;

  public FisheyeMirror() {
    super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
  }

  public void onEquip() {
    ++AbstractDungeon.player.energy.energyMaster;
  }

  public void onUnequip() {
    --AbstractDungeon.player.energy.energyMaster;
  }

  @Override
  public void atTurnStart() {
    addToBot(new MakeTempCardInDrawPileAction(new Dazed(), 1, true, true));
  }

  @Override
  public AbstractRelic makeCopy() {
    return new FisheyeMirror();
  }

  @Override
  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }
}
