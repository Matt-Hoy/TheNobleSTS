package thenoble.cards.common;

import static thenoble.cards.type.CachetCard.cachetAmount;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.AdvantagePower;
import thenoble.util.CardStats;

public class ImpeccableStyle extends NobleCard {
  public static final String ID = makeID("ImpeccableStyle");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, 1);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public ImpeccableStyle() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
    setCostUpgrade(0);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    if (magicNumber > 0 || cachetAmount() > 0) {
      addToBot(
          new ApplyPowerAction(
              monster, player, new AdvantagePower(monster, magicNumber), magicNumber));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new ImpeccableStyle();
  }
}
