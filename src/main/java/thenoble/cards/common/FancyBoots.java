package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.ConfidencePower;
import thenoble.util.CardStats;

public class FancyBoots extends NobleCard {
  public static final String ID = makeID("FancyBoots");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public FancyBoots() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
    setCostUpgrade(0);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    if (magicNumber > 0 || player.hasPower(ConfidencePower.POWER_ID)) {
      addToBot(new ApplyPowerAction(player, player, new DexterityPower(player, magicNumber)));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new FancyBoots();
  }
}
