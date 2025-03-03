package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RepairPower;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Stockpile extends CachetCard {
  public static final String ID = makeID("Stockpile");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 2);
  private static final int BLOCK = 15;
  private static final int UPG_BLOCK = 4;
  private static final int MAGIC = 7;
  private static final int UPG_MAGIC = 3;

  public Stockpile() {
    super(ID, INFO);

    setBlock(BLOCK, UPG_BLOCK);
    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new RepairPower(player, magicNumber)));
    exhaust = true;
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, block));
    if (cachetAmount() > 0) {
      triggerCachetEffect(player, monster, 1);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new Stockpile();
  }
}
