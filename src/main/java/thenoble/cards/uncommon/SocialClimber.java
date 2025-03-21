package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.SocialClimberPower;
import thenoble.util.CardStats;

public class SocialClimber extends NobleCard {
  public static final String ID = makeID("SocialClimber");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 3);
  private static final int MAGIC = 1;

  public SocialClimber() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(2);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new SocialClimberPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new SocialClimber();
  }
}
