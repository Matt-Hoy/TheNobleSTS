package thenoble.cards.common;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

import java.util.Objects;

public class SpectralHounds extends NobleCard {
  public static final String ID = makeID("SpectralHounds");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 1;

  public SpectralHounds() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(0);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new FetchAction(
            player.exhaustPile,
            (card) -> (!Objects.equals(card.cardID, SpectralHounds.ID)),
            magicNumber));
  }

  @Override
  public AbstractCard makeCopy() {
    return new SpectralHounds();
  }
}
