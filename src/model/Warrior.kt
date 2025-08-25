package src.model.warrior

import src.model.characterType.CharacterType
import src.model.characterType.Combatant


class Warrior (override val name: String, type: CharacterType = CharacterType.WARRIOR): Combatant{
    override var level = 1
    override var hp = type.baseHp
    override var attack = type.baseAtk
    override var defense = type.baseDefense
    override var skillMap = mutableMapOf<Int, (Combatant) -> Int>()
    override var skillNames = mutableMapOf<Int, String>()

    init{
        skillMap[1] = ::shieldSlam
        skillNames[1] = "Shield Slam"
    }

    override fun descriptChar(){
        println("A fearless melee fighter clad in steel, the Warrior thrives on the frontlines of battle. With unmatched endurance and raw strength!")
        println("Level: $level  Hp: $hp  Attack: $attack  Defense: $defense")
    }

    override fun listSkills(){
        for((index, name) in skillNames){
            println("$index. $name")
        }
    }

    fun useSkillById(skillId: Int, enemy: Combatant){
        val skill = skillMap[skillId]
        skill?.invoke(enemy)
    }

    fun shieldSlam(enemy: Combatant): Int{
        println("$name slams the shield to ${enemy.name} dealing ${attack / 2} damage!")
        println("$name's defense has gone up by %25! ($defense -> ${defense + (defense * 25 / 100)})")

        defense += defense * 25 / 100
        return attack / 2
    }

}