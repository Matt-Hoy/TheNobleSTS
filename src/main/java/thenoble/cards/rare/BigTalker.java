package thenoble.cards.rare;

// TODO: Demoralize Still Don't Work

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.BigTalkerPower;
import thenoble.util.CardStats;

import static thenoble.powers.ConfidencePower.getConfStacks;

public class BigTalker extends NobleCard {
  public static final String ID = makeID("BigTalker");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.RARE, CardTarget.SELF, 2);
  private static final int MAGIC = 0;
  private static final int UPG_MAGIC = 1;

  public BigTalker() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    if (magicNumber > 0 || getConfStacks() > 0) {
      addToBot(
          new ApplyPowerAction(
              player, player, new BigTalkerPower(player, magicNumber), magicNumber));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new BigTalker();
  }
}
