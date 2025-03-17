package thenoble.cards.uncommon;

import static thenoble.cards.type.CachetCard.cachetAmount;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class DuelistsStrike extends CachetCard {
  public static final String ID = makeID("DuelistsStrike");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 8;
  private static final int UPG_DAMAGE = 3;
  private static final int MAGIC = 1;

  public DuelistsStrike() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
    setMagic(MAGIC);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new DexterityPower(player, magicNumber)));
    exhaust = true;
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    if (cachetAmount() > 0) {
      triggerCachetEffect(player, monster, 1);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new DuelistsStrike();
  }
}
