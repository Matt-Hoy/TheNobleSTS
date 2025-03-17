package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.ConfidencePower;
import thenoble.util.CardStats;

public class Condescend extends NobleCard {
  public static final String ID = makeID("Condescend");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 4;
  private static final int UPG_DAMAGE = 1;
  private static final int BLOCK = 2;
  private static final int UPG_BLOCK = 1;
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public Condescend() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
    setBlock(BLOCK, UPG_BLOCK);
    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    addToBot(new GainBlockAction(player, player, block));
    if (magicNumber != 0 || player.hasPower(ConfidencePower.POWER_ID)) {
      addToBot(
          new ApplyPowerAction(
              monster,
              player,
              new VulnerablePower(monster, magicNumber, false),
              magicNumber,
              true,
              AbstractGameAction.AttackEffect.NONE));
      addToBot(
          new ApplyPowerAction(
              monster,
              player,
              new WeakPower(monster, magicNumber, false),
              magicNumber,
              true,
              AbstractGameAction.AttackEffect.NONE));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new Condescend();
  }
}
