package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Commission extends NobleCard {
  public static final String ID = makeID("Commission");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 2);

  public Commission() {
    super(ID, INFO);

    setCostUpgrade(1);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Commission();
  }
}
