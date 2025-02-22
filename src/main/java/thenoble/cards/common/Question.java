package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Question extends CachetCard {
  public static final String ID = makeID("Question");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 7;
  private static final int UPG_DAMAGE = 3;
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public Question() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(monster, player, new StrengthPower(monster, -magicNumber)));
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
      triggerCachetEffect(player, monster, 1);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new Question();
  }
}
