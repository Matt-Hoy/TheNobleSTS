package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class OpenBar extends NobleCard {
  public static final String ID = makeID("OpenBar");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, 1);
  private static final int MAGIC = 3;

  public OpenBar() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(0);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new RegenPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new OpenBar();
  }
}
