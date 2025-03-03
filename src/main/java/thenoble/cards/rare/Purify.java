package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Purify extends CachetCard {
  public static final String ID = makeID("Purify");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, 1);
  private static final int BLOCK = 5;

  public Purify() {
    super(ID, INFO);

    setBlock(BLOCK);
    setCostUpgrade(0);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    for (AbstractCard card : player.hand.group) {
      if (card.type == CardType.STATUS || card.type == CardType.CURSE) {
        addToBot(new ExhaustSpecificCardAction(card, player.hand));
      }
    }
    for (AbstractCard card : player.discardPile.group) {
      if (card.type == CardType.STATUS || card.type == CardType.CURSE) {
        addToBot(new ExhaustSpecificCardAction(card, player.discardPile));
      }
    }
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    int cachetCount = cachetAmount();
    addToBot(new GainBlockAction(player, player, block));
    if (cachetCount > 0) {
      triggerCachetEffect(player, monster, 1);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new Purify();
  }
}
