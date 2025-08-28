package src.game

import src.model.battleManager.BattleManager
import src.model.specs.Warrior
import src.model.specs.Mage
import src.model.specs.Ranger
import src.model.specs.Thief
import src.model.boss.Boss
import src.model.bossType.BossType


fun main(){


    val battleManager = BattleManager()

    // Creating prebuilt characters
    val warrior = Warrior("Thorin Ironshield")
    val mage = Mage("Luna Firelord")
    val ranger = Ranger("Robin Sharparrow")
    val thief = Thief("Dark Shadow")
    // Creating prebuilt bosses
    val normalBoss = Boss("Ice King", BossType.NORMAL)
    val eliteBoss = Boss("Giant Golem", BossType.ELITE)
    val legendaryBoss = Boss("Crimson Dragon", BossType.LEGENDARY)
    val mythicBoss = Boss("The Dark Lord", BossType.MYTHIC)
    // Running combatant selection function
    val(combatant1, combatant2) = battleManager.pickCombatants(listOf(warrior, mage, ranger, thief, normalBoss, eliteBoss, legendaryBoss, mythicBoss))
    // Taking the return values of combatant selection function and running duel function
    battleManager.duel(combatant1, combatant2)


}