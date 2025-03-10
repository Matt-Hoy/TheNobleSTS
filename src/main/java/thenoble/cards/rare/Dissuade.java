package thenoble.cards.rare;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import java.util.ArrayList;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Dissuade extends CachetCard {
  public static final String ID = makeID("Dissuade");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY, 2);
  private static final int BLOCK = 18;
  private static final int MAGIC = 1;

  public Dissuade() {
    super(ID, INFO);

    setBlock(BLOCK);
    setMagic(MAGIC);
    setCostUpgrade(1);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, ArrayList<AbstractMonster> monsters) {
    addToBot(
        new AllEnemyApplyPowerAction(
            player,
            -magicNumber,
            (individualMonster) -> (new StrengthPower(individualMonster, -magicNumber))));
    exhaust = true;
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    int cachetCount = cachetAmount();
    addToBot(new GainBlockAction(player, block));
    if (cachetCount > 0) {
      triggerCachetEffect(player, AbstractDungeon.getMonsters().monsters, 1);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new Dissuade();
  }
}
