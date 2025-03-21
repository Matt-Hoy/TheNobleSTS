package thenoble.powers;

import static thenoble.TheNoble.makeID;
import static thenoble.cards.type.CachetCard.cachetAmount;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;

public class SillyAffectPower extends BasePower {
  public static final String POWER_ID = makeID("SillyAffect");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public SillyAffectPower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
    if (info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0) {
      flash();
      if (amount > 0 || cachetAmount() > 0) {
        addToBot(
            new ApplyPowerAction(
                target, AbstractDungeon.player, new WeakPower(target, amount, false)));
      }
    }
  }

  public void updateDescription() {
    description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
