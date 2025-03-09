package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class Distract extends NobleCard {
  public static final String ID = makeID("Distract");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);
  private static final int BLOCK = 8;
  private static final int UPG_BLOCK = 4;
  private static final int MAGIC = 2;

  public Distract() {
    super(ID, INFO);

    setBlock(BLOCK, UPG_BLOCK);
    setMagic(MAGIC);
  }

  @Override
  public void triggerOnExhaust() {
    addToBot(
        new ApplyPowerAction(
            AbstractDungeon.player,
            AbstractDungeon.player,
            new DexterityPower(AbstractDungeon.player, magicNumber)));
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, block));
  }

  @Override
  public AbstractCard makeCopy() {
    return new Distract();
  }
}
