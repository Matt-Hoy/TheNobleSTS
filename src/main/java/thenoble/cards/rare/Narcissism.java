package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.NarcissismPower;
import thenoble.util.CardStats;

public class Narcissism extends NobleCard {
  public static final String ID = makeID("Narcissism");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.RARE, CardTarget.SELF, 3);
  private static final int MAGIC = 1;

  public Narcissism() {
    super(ID, INFO);

    setMagic(MAGIC);
    setInnate(false, true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new NarcissismPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Narcissism();
  }
}
