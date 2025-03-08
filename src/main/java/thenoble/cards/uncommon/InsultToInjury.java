package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class InsultToInjury extends NobleCard {
  public static final String ID = makeID("InsultToInjury");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 0);
  private static final int DAMAGE = 2;
  private static final int UPG_DAMAGE = 2;

  public InsultToInjury() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.BLUNT_LIGHT,
            true));
  }

  @Override
  public void triggerOnCardPlayed(AbstractCard cardPlayed) {
    if (cardPlayed.type == CardType.SKILL) {
      addToBot(new DiscardToHandAction(this));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new InsultToInjury();
  }
}
