package thenoble.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import thenoble.cards.type.CachetCard;
import thenoble.character.MyCharacter;
import thenoble.util.CardStats;

public class AntiquePistol extends CachetCard {
  public static final String ID = makeID("AntiquePistol");
  private static final CardStats INFO =
      new CardStats(
          MyCharacter.Meta.CARD_COLOR, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, 1);
  private static final int MAGIC = 4;
  private static final int UPG_MAGIC = 7;
  private static final int CACHET_TIMES = 1;

  private static class AntiquePistolAction extends AbstractGameAction {
    private final int incAmount;
    private final AbstractCard activeCard;

    public AntiquePistolAction(int incAmount, AbstractCard activeCard) {
      this.incAmount = incAmount;
      this.activeCard = activeCard;
    }

    @Override
    public void update() {
      for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
        if (card.uuid == activeCard.uuid) {
          card.misc += incAmount;
          card.applyPowers();
          card.baseDamage = card.misc;
          card.isDamageModified = false;
        }
      }
      for (AbstractCard card : GetAllInBattleInstances.get(activeCard.uuid)) {
        card.misc += incAmount;
        card.applyPowers();
        card.baseDamage = card.misc;
      }
      // Never delete this. For some reason this prevents infinite update.
      tickDuration();
    }
  }

  @Override
  public void cachetEffect(AbstractPlayer player, AbstractMonster monster) {
    addToBot(new AntiquePistolAction(magicNumber, this));
  }

  public AntiquePistol() {
    super(ID, INFO);
    misc = 14;
    setDamage(misc);
    setMagic(MAGIC, UPG_MAGIC);
    setExhaust(true);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    int cachetCount = cachetAmount();
    this.addToBot(
        new VFXAction(new MindblastEffect(player.dialogX, player.dialogY, player.flipHorizontal)));
    addToBot(
        new DamageAction(
            monster,
            new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.NONE));
    if (cachetCount > 0) {
      triggerCachetEffect(player, monster, CACHET_TIMES);
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new AntiquePistol();
  }
}
