package src.game

import src.model.warrior.Warrior
//import src.model.Arena


fun main(){

    //val arena = Arena()

    val warrior1 = Warrior("Bert")
    val warrior2 = Warrior("Makan")

    warrior1.listSkills()

    warrior1.useSkillById(1, warrior2)


    //arena.startDuel(warrior1, warrior2)


}