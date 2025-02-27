package thenoble.cards.uncommon;

import static thenoble.powers.ConfidencePower.getConfStacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.ArrayList;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class WideSwipe extends CachetCard {
  public static final String ID = makeID("WideSwipe");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 8;
  private static final int UPG_DAMAGE = 2;

  public WideSwipe() {
    super(ID, INFO);
    if (AbstractDungeon.player != null && AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
      int confStacks = getConfStacks();
      if (confStacks > 0) {
        target = CardTarget.ALL_ENEMY;
      }
    }

    setDamage(DAMAGE, UPG_DAMAGE);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, ArrayList<AbstractMonster> monsters) {
    for (AbstractMonster individualMonster : monsters) {
      addToBot(
          new DamageAction(
              individualMonster,
              new DamageInfo(player, damage * 2, DamageInfo.DamageType.NORMAL),
              AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    int cachetCount = cachetAmount();
    if (cachetCount > 0) {
      triggerCachetEffect(player, AbstractDungeon.getMonsters().monsters, 1);
    } else {
      addToBot(
          new DamageAction(
              monster,
              new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
              AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
  }

  @Override
  public void onConfidenceUpdated(AbstractPower power) {
    target = CardTarget.ALL_ENEMY;
  }

  @Override
  public void onConfidenceRemoved() {
    target = CardTarget.ENEMY;
  }

  @Override
  public AbstractCard makeCopy() {
    return new WideSwipe();
  }
}
