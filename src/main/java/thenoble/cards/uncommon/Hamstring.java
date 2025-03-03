package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Hamstring extends NobleCard {
  public static final String ID = makeID("Hamstring");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 3);
  private static final int DAMAGE = 3;
  private static final int UPG_DAMAGE = 2;

  public Hamstring() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
    setCostUpgrade(2);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    addToBot(new ApplyPowerAction(monster, player, new SlowPower(monster, 0), 0));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Hamstring();
  }
}
