package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class TargetedCriticism extends CachetCard {
  public static final String ID = makeID("TargetedCriticism");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, 2);
  private static final int DAMAGE = 4;
  private static final int UPG_DAMAGE = 1;

  public TargetedCriticism() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    int stacks = 0;
    for (AbstractMonster individualMonster : AbstractDungeon.getMonsters().monsters) {
      stacks += countAllPowers(individualMonster);
    }
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, (damage * stacks), DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.FIRE));
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    if (cachetAmount() > 0) {
      triggerCachetEffect(player, monster, 1);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new TargetedCriticism();
  }
}
