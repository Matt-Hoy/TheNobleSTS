package thenoble.cards.uncommon;

import static thenoble.cards.type.CachetCard.cachetAmount;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class TheDuster extends NobleCard {
  public static final String ID = makeID("TheDuster");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public TheDuster() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    if (magicNumber > 0 || cachetAmount() > 0) {
      addToBot(new ApplyPowerAction(player, player, new BufferPower(player, magicNumber)));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new TheDuster();
  }
}
