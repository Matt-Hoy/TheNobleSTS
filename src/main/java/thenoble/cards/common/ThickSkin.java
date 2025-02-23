package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class ThickSkin extends NobleCard {
  public static final String ID = makeID("ThickSkin");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 0);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public ThickSkin() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new ApplyPowerAction(
            player, player, new MetallicizePower(player, magicNumber), magicNumber, true));
  }

  @Override
  public AbstractCard makeCopy() {
    return new ThickSkin();
  }
}
