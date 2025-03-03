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
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class RiskyBiscuit extends CachetCard {
  public static final String ID = makeID("RiskyBiscuit");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 3);
  private static final int DAMAGE = 18;
  private static final int UPG_DAMAGE = 9;

  public RiskyBiscuit() {
    super(ID, INFO);
    if (AbstractDungeon.player != null && AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
      int confStacks = getConfStacks();
      modifyCostForCombat(-confStacks);
    }
    setDamage(DAMAGE, UPG_DAMAGE);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    if (cachetAmount() > 0) {
      triggerCachetEffect(player, monster, 0);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new RiskyBiscuit();
  }

  @Override
  public void onConfidenceUpdated(AbstractPower power) {
    modifyCostForCombat(-1);
  }

  @Override
  public void onConfidenceRemoved() {
    cost = baseCost;
    setCostForTurn(cost);
    isCostModified = false;
  }
}
