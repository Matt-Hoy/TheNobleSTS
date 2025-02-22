package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class AdvantagePower extends BasePower {
  public static final String POWER_ID = makeID("Advantage");
  private static final PowerType TYPE = PowerType.DEBUFF;
  private static final boolean TURN_BASED = true;

  public AdvantagePower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void wasHPLost(DamageInfo info, int damageAmount) {
    if (info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0) {
      flash();
      addToTop(
          new ApplyPowerAction(
              AbstractDungeon.player,
              AbstractDungeon.player,
              new DexterityPower(AbstractDungeon.player, amount)));
    }
  }

  @Override
  public void atEndOfRound() {
    addToBot(new RemoveSpecificPowerAction(owner, AbstractDungeon.player, AdvantagePower.POWER_ID));
  }

  public void updateDescription() {
    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
