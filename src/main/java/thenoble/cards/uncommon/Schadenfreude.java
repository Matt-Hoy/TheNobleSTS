package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.SchadenfreudePower;
import thenoble.util.CardStats;

public class Schadenfreude extends NobleCard {
  public static final String ID = makeID("Schadenfreude");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 2);
  private static final int MAGIC = 2;
  private static final int UPG_MAGIC = 1;

  public Schadenfreude() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new ApplyPowerAction(
            player, player, new SchadenfreudePower(player, magicNumber), magicNumber));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Schadenfreude();
  }
}
