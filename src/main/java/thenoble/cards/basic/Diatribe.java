package thenoble.cards.basic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Diatribe extends NobleCard {
  public static final String ID = makeID("Diatribe");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.BASIC, CardTarget.ALL_ENEMY, 1);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 0;

  public Diatribe() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
    setCostUpgrade(0);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    for (AbstractMonster individualMonster : AbstractDungeon.getCurrRoom().monsters.monsters) {
      addToBot(
          new ApplyPowerAction(
              individualMonster,
              player,
              new VulnerablePower(individualMonster, magicNumber, false),
              magicNumber,
              true,
              AbstractGameAction.AttackEffect.NONE));
      addToBot(
          new ApplyPowerAction(
              individualMonster,
              player,
              new WeakPower(individualMonster, magicNumber, false),
              magicNumber,
              true,
              AbstractGameAction.AttackEffect.NONE));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new Diatribe();
  }
}
