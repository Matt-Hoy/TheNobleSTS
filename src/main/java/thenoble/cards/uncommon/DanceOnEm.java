package thenoble.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thenoble.cards.NobleCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

/*
"${modID}:ExampleCard": {
  "NAME": "Name",
  "DESCRIPTION": "Description. NL Second line.",
  "UPGRADE_DESCRIPTION": "This will automatically be used if the card is upgraded. Remove it if unneeded.",
  "EXTENDED_DESCRIPTION": [
    "You can put more text in here.",
    "If you need to use it for stuff.",
    "Blizzard is a good example for this."
  ]
},
 */

public class DanceOnEm extends NobleCard {
  public static final String ID = makeID("DanceOnEm");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR,
          CardType.SKILL,
          CardRarity.UNCOMMON,
          CardTarget.ALL_ENEMY,
          2);
  private static final int BLOCK = 5;
  private static final int UPG_BLOCK = 3;
  private static final int MAGIC = 1;
  private static final int UPG_MAGIC = 1;

  public DanceOnEm() {
    super(ID, INFO);

    setBlock(BLOCK, UPG_BLOCK);
    setMagic(MAGIC, UPG_MAGIC);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new GainBlockAction(player, player, block));
    for (AbstractMonster individualMonster : AbstractDungeon.getCurrRoom().monsters.monsters) {
      addToBot(
          new ApplyPowerAction(
              individualMonster,
              player,
              new WeakPower(individualMonster, magicNumber, false),
              magicNumber));
      addToBot(
          new ApplyPowerAction(
              individualMonster,
              player,
              new StrengthPower(individualMonster, -magicNumber),
              -magicNumber));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new DanceOnEm();
  }
}
