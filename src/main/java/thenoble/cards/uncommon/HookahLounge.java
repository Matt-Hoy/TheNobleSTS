package thenoble.cards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class HookahLounge extends NobleCard {
  public static final String ID = makeID("HookahLounge");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR,
          CardType.SKILL,
          CardRarity.UNCOMMON,
          CardTarget.ALL_ENEMY,
          0);
  private static final int MAGIC = 2;
  private static final int UPG_MAGIC = 2;

  public HookahLounge() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new AllEnemyApplyPowerAction(
            player,
            magicNumber,
            (individualMonster) -> (new PoisonPower(individualMonster, player, magicNumber))));
  }

  @Override
  public AbstractCard makeCopy() {
    return new HookahLounge();
  }
}
