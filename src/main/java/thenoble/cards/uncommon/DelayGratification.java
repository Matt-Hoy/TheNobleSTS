package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class DelayGratification extends NobleCard {
  public static final String ID = makeID("DelayGratification");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 2);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public DelayGratification() {
    super(ID, INFO);

    misc = 0;
    setDamage(misc);
    setMagic(MAGIC, UPG_MAGIC);
    setEthereal(true);
    setExhaust(true);
    setCostUpgrade(1);
  }

  @Override
  public void triggerWhenDrawn() {
    addToBot(new IncreaseDamageAction(magicNumber, this));
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SMASH));
  }

  @Override
  public AbstractCard makeCopy() {
    return new DelayGratification();
  }
}
