// package thenoble.cards.test;
//
// import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
// import com.megacrit.cardcrawl.cards.AbstractCard;
// import com.megacrit.cardcrawl.characters.AbstractPlayer;
// import com.megacrit.cardcrawl.monsters.AbstractMonster;
// import thenoble.cards.NobleCard;
// import thenoble.character.MyCharacter;
// import thenoble.powers.ConfidencePower;
// import thenoble.util.CardStats;
//
// public class ConfidenceTest extends NobleCard {
//  public static final String ID = makeID("ConfidenceTest");
//  private static final CardStats INFO =
//      new CardStats(
//          MyCharacter.Meta.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 0);
//
//  public ConfidenceTest() {
//    super(ID, INFO);
//  }
//
//  @Override
//  public void use(AbstractPlayer player, AbstractMonster monster) {
//    addToBot(new ApplyPowerAction(player, player, new ConfidencePower(player, 1)));
//  }
//
//  @Override
//  public AbstractCard makeCopy() {
//    return new ConfidenceTest();
//  }
// }
