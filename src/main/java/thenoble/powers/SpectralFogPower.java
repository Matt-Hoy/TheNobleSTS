package thenoble.powers;

import static thenoble.TheNoble.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SpectralFogPower extends BasePower {
  public static final String POWER_ID = makeID("SpectralFog");
  private static final PowerType TYPE = PowerType.BUFF;
  private static final boolean TURN_BASED = false;

  public SpectralFogPower(AbstractCreature owner, int amount) {
    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
  }

  @Override
  public void onUseCard(AbstractCard card, UseCardAction action) {
    if (card.exhaust) {
      flash();
      addToBot(
          new DamageAllEnemiesAction(
              AbstractDungeon.player,
              DamageInfo.createDamageMatrix(amount, true),
              DamageInfo.DamageType.THORNS,
              AbstractGameAction.AttackEffect.NONE));
    }
  }

  public void updateDescription() {
    description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
  }
}
