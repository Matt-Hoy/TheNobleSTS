package thenoble.cards.rare;

import static thenoble.powers.ConfidencePower.getConfStacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class FuzzyIdeas extends NobleCard {
  public static final String ID = makeID("FuzzyIdeas");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, 2);
  private static final int BLOCK = 16;
  private static final int UPG_BLOCK = 8;
  private static final int MAGIC = 0;

  public FuzzyIdeas() {
    super(ID, INFO);

    setBlock(BLOCK, UPG_BLOCK);
    setMagic(MAGIC);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, player, block));
    int confStacks = getConfStacks();
    if (confStacks > 0) {
      addToBot(new ApplyPowerAction(player, player, new BlurPower(player, magicNumber)));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new FuzzyIdeas();
  }
}
