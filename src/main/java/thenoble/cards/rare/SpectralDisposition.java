package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.SpectralDispositionPower;
import thenoble.util.CardStats;

public class SpectralDisposition extends NobleCard {
  public static final String ID = makeID("SpectralDisposition");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.RARE, CardTarget.SELF, 2);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public SpectralDisposition() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
    setCostUpgrade(0);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new ApplyPowerAction(player, player, new SpectralDispositionPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new SpectralDisposition();
  }
}
