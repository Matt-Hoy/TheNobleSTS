package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.MakingDonationsPower;
import thenoble.util.CardStats;

public class MakingDonations extends NobleCard {
  public static final String ID = makeID("MakingDonations");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.COMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 3;
  private static final int UPG_MAGIC = 2;

  public MakingDonations() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new MakingDonationsPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new MakingDonations();
  }
}
