package thenoble.cards.uncommon;

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
import thenoble.util.CardStats;

public class Backhand extends NobleCard {
  public static final String ID = makeID("Backhand");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 9;
  private static final int UPG_DAMAGE = 4;
  private static final int MAGIC = 2;

  public Backhand() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
    setMagic(MAGIC);
  }

  @Override
  public void triggerOnExhaust() {
    addToBot(
        new ApplyPowerAction(
            AbstractDungeon.player,
            AbstractDungeon.player,
            new StrengthPower(AbstractDungeon.player, magicNumber)));
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster, new DamageInfo(player, damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Backhand();
  }
}
