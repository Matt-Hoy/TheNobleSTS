package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
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
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, 0);
  private static final int DAMAGE = 3;
  private static final int UPG_DAMAGE = 2;

  public ThrowTheGlove() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ExpertiseAction(player, 10));
    if (!upgraded) {
      exhaust = true;
    }
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
      triggerCachetEffect(player, monster, cachetCount);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new ThrowTheGlove();
  }
}
