package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class ThrowTheGlove extends CachetCard {
  public static final String ID = makeID("ThrowTheGlove");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 0);
  private static final int DAMAGE = 3;
  private static final int UPG_DAMAGE = 2;
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public ThrowTheGlove() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new DrawCardAction(player, 1));
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    int cachetCount = cachetAmount();
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    if (cachetCount > 0) {
      if (magicNumber == 1) {
        addToBot(new DrawCardAction(player, 1));
      }
      triggerCachetEffect(player, monster, cachetCount);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new ThrowTheGlove();
  }
}
