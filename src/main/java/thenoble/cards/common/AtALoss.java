package thenoble.cards.common;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class AtALoss extends NobleCard {
  public static final String ID = makeID("AtALoss");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR,
          CardType.SKILL,
          CardRarity.UNCOMMON,
          CardTarget.ALL_ENEMY,
          1);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public AtALoss() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
    setCostUpgrade(0);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new AllEnemyApplyPowerAction(
            player,
            magicNumber,
            (individualMonster) -> (new ChokePower(individualMonster, magicNumber))));
  }

  @Override
  public AbstractCard makeCopy() {
    return new AtALoss();
  }
}
