package thenoble.cards.basic;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Dissemble extends NobleCard {
  public static final String ID = makeID("Dissemble");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, 1);
  private static final int BLOCK = 6;
  private static final int UPG_BLOCK = 3;

  public Dissemble() {
    super(ID, INFO);

    setBlock(BLOCK, UPG_BLOCK);

    tags.add(CardTags.STARTER_DEFEND);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, player, block));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Dissemble();
  }
}
