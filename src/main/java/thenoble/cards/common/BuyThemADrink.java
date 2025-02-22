package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class BuyThemADrink extends NobleCard {
  public static final String ID = makeID("BuyThemADrink");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, 1);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public BuyThemADrink() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
    setCostUpgrade(0);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    int totalCount =
        checkPowerCount(monster, VulnerablePower.POWER_ID)
            + checkPowerCount(monster, WeakPower.POWER_ID);
    if (totalCount > 0) {
      addToBot(
          new ApplyPowerAction(
              monster, player, new PoisonPower(monster, player, (totalCount * magicNumber))));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new BuyThemADrink();
  }
}
