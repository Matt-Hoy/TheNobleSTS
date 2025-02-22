package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.ConfidencePower;
import thenoble.util.CardStats;

public class Question extends NobleCard {
  public static final String ID = makeID("Question");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 7;
  private static final int UPG_DAMAGE = 2;
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public Question() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
    setMagic(MAGIC, UPG_MAGIC);
    setExhaust(true);
  }

  //  TODO: Haven't figured out the -0 Strength problem yet.
  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SMASH));
    if (magicNumber != 0 || player.hasPower(ConfidencePower.POWER_ID)) {
      addToBot(
          new ApplyPowerAction(
              monster,
              AbstractDungeon.player,
              new StrengthPower(AbstractDungeon.player, -magicNumber)));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new Question();
  }
}
