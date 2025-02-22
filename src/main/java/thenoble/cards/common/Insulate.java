package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Insulate extends NobleCard {
  public static final String ID = makeID("Insulate");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, 1);

  public Insulate() {
    super(ID, INFO);

    setCostUpgrade(0);
  }

  private int countAllPowers(AbstractMonster target) {
    int total = 0;
    for (AbstractPower individualPower : target.powers) {
      if (individualPower.type == AbstractPower.PowerType.DEBUFF) {
        total += individualPower.amount;
      }
    }
    return total;
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, player, countAllPowers(monster) + dexCheck(player)));
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
