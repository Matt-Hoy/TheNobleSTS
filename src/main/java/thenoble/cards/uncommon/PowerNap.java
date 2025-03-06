package thenoble.cards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.List;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class PowerNap extends NobleCard {
  public static final String ID = makeID("PowerNap");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public PowerNap() {
    super(ID, INFO);

    setMagic(MAGIC, UPG_MAGIC);
  }

  private void exhaustCard(List<AbstractCard> cards) {
    for (AbstractCard card : cards) {
      addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
    }
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new SelectCardsInHandAction(magicNumber, "Select card(s) to Exhaust.", this::exhaustCard));
    addToBot(new GainEnergyAction(magicNumber));
    addToBot(new DrawCardAction(magicNumber));
  }

  @Override
  public AbstractCard makeCopy() {
    return new PowerNap();
  }
}
