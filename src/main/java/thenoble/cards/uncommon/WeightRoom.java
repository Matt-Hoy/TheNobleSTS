package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.powers.WeightRoomPower;
import thenoble.util.CardStats;

public class WeightRoom extends NobleCard {
  public static final String ID = makeID("WeightRoom");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 3);
  private static final int MAGIC = 1;

  public WeightRoom() {
    super(ID, INFO);

    setMagic(MAGIC);
    setCostUpgrade(2);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new ApplyPowerAction(player, player, new WeightRoomPower(player, magicNumber)));
  }

  @Override
  public AbstractCard makeCopy() {
    return new WeightRoom();
  }
}
