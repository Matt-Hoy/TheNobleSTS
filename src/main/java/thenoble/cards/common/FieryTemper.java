package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class FieryTemper extends NobleCard {
  public static final String ID = makeID("FieryTemper");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 1);
  private static final int BLOCK = 8;
  private static final int UPG_BLOCK = 3;
  private static final int MAGIC = 2;
  private static final int UPG_MAGIC = 1;

  public FieryTemper() {
    super(ID, INFO);

    setBlock(BLOCK, UPG_BLOCK);
    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, player, block));
    addToBot(new ApplyPowerAction(player, player, new FlameBarrierPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new FieryTemper();
  }
}
