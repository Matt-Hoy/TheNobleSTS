// TODO: Demoralize is broken.

//  "${modID}:CuttingWords": {
//          "NAME": "Cutting Words",
//          "DESCRIPTIONS": [
//          "Whenever you play a skill, apply #b",
//          " #yDemoralize to ALL enemies."
//          ]
//          },

//  "${modID}:CuttingWords": {
//          "NAME": "Cutting Words",
//          "DESCRIPTION": "Whenever you play a skill, apply !M! Demoralize to ALL enemies.",
//          "UPGRADE_DESCRIPTION": "Innate. Whenever you play a skill, apply !M! Demoralize to ALL
// enemies."
//          },

// package thenoble.powers;
//
// import static thenoble.TheNoble.makeID;
//
// import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
// import com.megacrit.cardcrawl.actions.utility.UseCardAction;
// import com.megacrit.cardcrawl.cards.AbstractCard;
// import com.megacrit.cardcrawl.core.AbstractCreature;
// import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
// import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
// public class CuttingWordsPower extends BasePower {
//  public static final String POWER_ID = makeID("CuttingWords");
//  private static final PowerType TYPE = PowerType.BUFF;
//  private static final boolean TURN_BASED = false;
//
//  public CuttingWordsPower(AbstractCreature owner, int amount) {
//    super(POWER_ID, TYPE, TURN_BASED, owner, amount);
//  }
//
//  @Override
//  public void onUseCard(AbstractCard card, UseCardAction action) {
//    if (card.type == AbstractCard.CardType.SKILL) {
//      for (AbstractMonster individualMonster : AbstractDungeon.getMonsters().monsters) {
//        addToBot(
//            new ApplyPowerAction(
//                individualMonster, owner, new DemoralizePower(individualMonster, amount),
// amount));
//      }
//    }
//  }
//
//  public void updateDescription() {
//    this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
//  }
// }
