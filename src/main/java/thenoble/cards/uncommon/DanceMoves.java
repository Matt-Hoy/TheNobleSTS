package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class DanceMoves extends NobleCard {
  public static final String ID = makeID("DanceMoves");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 2;

  public DanceMoves() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(0);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new DrawCardAction(player, magicNumber));
  }

  @Override
  public AbstractCard makeCopy() {
    return new DanceMoves();
  }
}
