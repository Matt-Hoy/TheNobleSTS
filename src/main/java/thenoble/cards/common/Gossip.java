package thenoble.cards.common;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Gossip extends NobleCard {
  public static final String ID = makeID("Gossip");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY, 0);
  private static final int BLOCK = 2;
  private static final int UPG_BLOCK = 1;
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public Gossip() {
    super(ID, INFO);

    setBlock(BLOCK, UPG_BLOCK);
    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, block));
    addToBot(
        new AllEnemyApplyPowerAction(
            player,
            magicNumber,
            (individualMonster) -> (new PoisonPower(individualMonster, player, magicNumber))));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Gossip();
  }
}
