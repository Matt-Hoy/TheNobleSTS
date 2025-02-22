package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.SocialClimberPower;
import thenoble.util.CardStats;

/*
"${modID}:ExampleCard": {
  "NAME": "Name",
  "DESCRIPTION": "Description. NL Second line.",
  "UPGRADE_DESCRIPTION": "This will automatically be used if the card is upgraded. Remove it if unneeded.",
  "EXTENDED_DESCRIPTION": [
    "You can put more text in here.",
    "If you need to use it for stuff.",
    "Blizzard is a good example for this."
  ]
},
 */

public class SocialClimber extends NobleCard {
  public static final String ID = makeID("SocialClimber");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.RARE, CardTarget.SELF, 3);
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
