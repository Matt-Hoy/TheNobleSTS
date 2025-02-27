package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Flaky extends NobleCard {
  public static final String ID = makeID("Flaky");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, 3);
  private static final int MAGIC = 1;

  public Flaky() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(2);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new IntangiblePlayerPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Flaky();
  }
}
