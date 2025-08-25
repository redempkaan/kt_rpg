package src.game

import src.model.warrior.Warrior



fun main(){

    val warrior1 = Warrior("Bert")
    val warrior2 = Warrior("Makan")

    warrior1.listSkills()

    warrior1.useSkillById(1, warrior2)


}