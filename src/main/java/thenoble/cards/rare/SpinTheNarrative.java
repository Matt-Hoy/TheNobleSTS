package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class SpinTheNarrative extends CachetCard {
  public static final String ID = makeID("SpinTheNarrative");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 4;
  private static final int UPG_DAMAGE = 1;
  private static final int MAGIC = 1;

  public SpinTheNarrative() {
    super(ID, INFO);
    setDamage(DAMAGE, UPG_DAMAGE);
    setMagic(MAGIC);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    if (cachetAmount() > 0) {
      triggerCachetEffect(player, monster, player.hand.size());
    } else {
      addToBot(
          new DamageAction(
              monster,
              new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
              AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new SpinTheNarrative();
  }
}
