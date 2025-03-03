package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Unselfconscious extends NobleCard {
  public static final String ID = makeID("Unselfconscious");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 1;

  public Unselfconscious() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(0);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new DexterityPower(player, magicNumber)));
    addToBot(new ApplyPowerAction(player, player, new StrengthPower(player, magicNumber)));
    addToBot(new MakeTempCardInDrawPileAction(new Dazed(), 2, true, true));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Unselfconscious();
  }
}
