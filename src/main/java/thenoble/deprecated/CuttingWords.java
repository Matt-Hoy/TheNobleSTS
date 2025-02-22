// TODO: Demoralize doesn't work

// package thenoble.cards.rare;
//
// import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
// import com.megacrit.cardcrawl.cards.AbstractCard;
// import com.megacrit.cardcrawl.characters.AbstractPlayer;
// import com.megacrit.cardcrawl.monsters.AbstractMonster;
// import thenoble.cards.BaseCard;
// import thenoble.character.MyCharacter;
// import thenoble.powers.CuttingWordsPower;
// import thenoble.deprecated.DemoralizePower;
// import thenoble.util.CardStats;
//
// public class CuttingWords extends BaseCard {
//  public static final String ID = makeID("CuttingWords");
//  private static final CardStats INFO =
//      new CardStats(
//          MyCharacter.Meta.CARD_COLOR, CardType.POWER, CardRarity.RARE, CardTarget.SELF, 2);
//  private static final int MAGIC = 1;
//
//  public CuttingWords() {
//    super(ID, INFO);
//
//    setMagic(MAGIC);
//    setInnate(false, true);
//  }
//
//  @Override
//  public void use(AbstractPlayer player, AbstractMonster monster) {
//    addToBot(
//        new ApplyPowerAction(
//            player, player, new CuttingWordsPower(player, magicNumber), magicNumber));
//  }
//
//  @Override
//  public AbstractCard makeCopy() {
//    return new CuttingWords();
//  }
// }
