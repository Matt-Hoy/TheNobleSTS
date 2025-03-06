package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.SillyAffectPower;
import thenoble.util.CardStats;

import static thenoble.cards.type.CachetCard.cachetAmount;

public class SillyAffect extends NobleCard {
  public static final String ID = makeID("SillyAffect");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.COMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public SillyAffect() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    if (cachetAmount() > 0 || magicNumber > 0) {
      addToBot(new ApplyPowerAction(player, player, new SillyAffectPower(player, magicNumber)));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new SillyAffect();
  }
}
