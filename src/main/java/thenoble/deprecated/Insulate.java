package thenoble.deprecated;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

//  "${modID}:Insulate": {
//          "NAME": "Insulate",
//          "DESCRIPTION": "Gain 2 Block for every stack of every debuff on target enemy."
//          },

public class Insulate extends NobleCard {
  public static final String ID = makeID("Insulate");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, 1);

  public Insulate() {
    super(ID, INFO);

    setCostUpgrade(0);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, player, countAllPowerStacks(monster) + dexCheck(player)));
  }

  //  For some reason the default setBlock is not registering Dexterity
  private int dexCheck(AbstractPlayer player) {
    AbstractPower dexterity = player.getPower(DexterityPower.POWER_ID);
    if (dexterity == null) {
      return 0;
    }
    return dexterity.amount;
  }

  @Override
  public AbstractCard makeCopy() {
    return new Insulate();
  }
}
