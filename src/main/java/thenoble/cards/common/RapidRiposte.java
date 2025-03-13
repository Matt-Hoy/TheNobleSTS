package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class RapidRiposte extends NobleCard {
  public static final String ID = makeID("RapidRiposte");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 5;
  private static final int UPG_DAMAGE = 2;
  private static final int BLOCK = 5;
  private static final int UPG_BLOCK = 2;

  public RapidRiposte() {
    super(ID, INFO);

    setDamage(DAMAGE, UPG_DAMAGE);
    setBlock(BLOCK, UPG_BLOCK);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    addToBot(new GainBlockAction(player, player, block));
  }

  @Override
  public AbstractCard makeCopy() {
    return new RapidRiposte();
  }
}
