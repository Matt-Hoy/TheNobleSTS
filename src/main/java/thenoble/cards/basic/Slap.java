package thenoble.cards.basic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Slap extends NobleCard {
  public static final String ID = makeID("Slap");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 5;
  private static final int UPG_DAMAGE = 3;

  public Slap() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);

    tags.add(CardTags.STARTER_STRIKE);
    tags.add(CardTags.STRIKE);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Slap();
  }
}
