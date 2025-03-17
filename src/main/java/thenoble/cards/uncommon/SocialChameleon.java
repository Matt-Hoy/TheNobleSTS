package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class SocialChameleon extends CachetCard {
  public static final String ID = makeID("SocialChameleon");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 2);
  private static final int MAGIC = 1;

  public SocialChameleon() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(1);
    setExhaust(true);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    AbstractCard newCard =
        AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER).makeCopy();
    newCard.costForTurn = 0;
    newCard.isCostModified = true;
    addToBot(new MakeTempCardInHandAction(newCard));
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    if (cachetAmount() > 0) {
      triggerCachetEffect(player, monster, magicNumber);
    } else {
      AbstractCard newCard =
          AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER).makeCopy();
      addToBot(new MakeTempCardInHandAction(newCard));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new SocialChameleon();
  }
}
