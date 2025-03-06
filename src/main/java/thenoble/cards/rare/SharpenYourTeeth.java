package thenoble.cards.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.actions.SharpenYourTeethAction;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class SharpenYourTeeth extends NobleCard {
  public static final String ID = makeID("SharpenYourTeeth");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, 1);
  private static final int MAGIC = 2;
  private static final int UPG_MAGIC = 1;

  public SharpenYourTeeth() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new SharpenYourTeethAction(magicNumber));
  }

  @Override
  public AbstractCard makeCopy() {
    return new SharpenYourTeeth();
  }
}
