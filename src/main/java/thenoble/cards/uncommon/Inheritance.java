package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.InheritancePower;
import thenoble.util.CardStats;

public class Inheritance extends NobleCard {
  public static final String ID = makeID("Inheritance");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 1;

  public Inheritance() {
    super(ID, INFO);

    setMagic(MAGIC);
    setInnate(false, true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new InheritancePower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Inheritance();
  }
}
