package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.ConfidencePower;
import thenoble.util.CardStats;

public class AgreeToDisagree extends NobleCard {
  public static final String ID = makeID("AgreeToDisagree");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, 2);
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public AgreeToDisagree() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
    setCostUpgrade(1);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    int vulnCount = checkPowerCount(monster, VulnerablePower.POWER_ID);
    addToBot(new RemoveSpecificPowerAction(monster, player, VulnerablePower.POWER_ID));
    if (vulnCount > 0 || player.hasPower(ConfidencePower.POWER_ID)) {
      addToBot(new ApplyPowerAction(player, player, new DexterityPower(player, vulnCount)));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new AgreeToDisagree();
  }
}
