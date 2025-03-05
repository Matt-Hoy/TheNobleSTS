package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class EyeForQuality extends NobleCard {
  public static final String ID = makeID("EyeForQuality");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 0);
  private static final int MAGIC = 1;

  public EyeForQuality() {
    super(ID, INFO);

    setMagic(MAGIC);
    setExhaust(true, false);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new ArtifactPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new EyeForQuality();
  }
}
