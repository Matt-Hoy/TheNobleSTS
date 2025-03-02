package thenoble.cards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Predict extends NobleCard {
  public static final String ID = makeID("Predict");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR,
          CardType.SKILL,
          CardRarity.UNCOMMON,
          CardTarget.ALL_ENEMY,
          1);
  private static final int MAGIC = 1;

  public Predict() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(0);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new AllEnemyApplyPowerAction(
            player,
            magicNumber,
            (individualMonster) -> new BlockReturnPower(individualMonster, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Predict();
  }
}
