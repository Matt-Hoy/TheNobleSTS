package thenoble.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class SideEye extends NobleCard {
  public static final String ID = makeID("SideEye");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, 0);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 2;
  private static final int DRAW_AMOUNT = 1;

  public SideEye() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(monster, player, new PoisonPower(monster, player, magicNumber)));
    addToBot(new DrawCardAction(player, DRAW_AMOUNT));
  }

  @Override
  public AbstractCard makeCopy() {
    return new SideEye();
  }
}
