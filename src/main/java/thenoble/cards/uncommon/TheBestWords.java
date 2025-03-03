package thenoble.cards.uncommon;

import static thenoble.cards.type.CachetCard.cachetAmount;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class TheBestWords extends NobleCard {
  public static final String ID = makeID("TheBestWords");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);
  private static final int BLOCK = 8;
  private static final int UPG_BLOCK = 3;
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public TheBestWords() {
    super(ID, INFO);

    setBlock(BLOCK, UPG_BLOCK);
    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, block));
    if (magicNumber > 0 || cachetAmount() > 0) {
      addToBot(
          new AllEnemyApplyPowerAction(
              player,
              magicNumber,
              (individualMonster) -> (new ChokePower(individualMonster, magicNumber))));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new TheBestWords();
  }
}
