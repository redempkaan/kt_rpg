package src.model.specs

import src.model.characterType.CharacterType
import src.model.characterType.Combatant
import kotlin.collections.iterator
import kotlin.random.Random

class Warrior (override val name: String, type: CharacterType = CharacterType.WARRIOR): Combatant {
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
            println("$index. $name\n")
        }
    }

    override fun useSkillById(skillId: Int, enemy: Combatant): Int{
        val skill = skillMap[skillId]
        return skill!!.invoke(enemy)
    }

    override fun calculateDamage(enemy: Combatant): Int {
        var multiplier = 1
        if(Random.nextDouble(0.0, 1.0) > 0.9){
            multiplier = 2
        }
        return (attack - (enemy.defense / 10)) * multiplier
    }

    fun shieldSlam(enemy: Combatant): Int{
        val damage = calculateDamage(enemy)

        println("\n${if (damage > attack) "[CRIT!]" else ""}$name slams the shield to ${enemy.name} dealing $damage damage!")
        println("$name's defense has gone up by %25! ($defense -> ${defense + (defense * 25 / 100)})\n")

        if(defense < 200){
            defense += defense * 25 / 100
        }

        return damage
    }

}