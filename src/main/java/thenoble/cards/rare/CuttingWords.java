package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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

public class CuttingWords extends NobleCard {
  public static final String ID = makeID("CuttingWords");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, 1);
  private static final int DAMAGE = 11;
  private static final int UPG_DAMAGE = 2;

  public CuttingWords() {
    super(ID, INFO);
    //    isMultiDamage = true;

    setDamage(DAMAGE, UPG_DAMAGE);
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    int cacheCount = cachetAmount();
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    if (cacheCount > 0) {
      triggerCachetEffect(player, monster, cacheCount);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new CuttingWords();
  }
}
