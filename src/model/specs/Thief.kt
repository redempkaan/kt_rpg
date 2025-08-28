package src.model.specs

import src.model.characterType.CharacterType
import src.model.characterType.Combatant
import kotlin.collections.iterator
import kotlin.random.Random

class Thief (override val name: String, type: CharacterType = CharacterType.THIEF): Combatant {
    override var level = 1
    override var hp = type.baseHp
    override var attack = type.baseAtk
    override var defense = type.baseDefense
    override var skillMap = mutableMapOf<Int, (Combatant) -> Int>()
    override var skillNames = mutableMapOf<Int, String>()
    // Adding skill function references and names to related lists
    init{
        skillMap[1] = ::shadowStrike
        skillNames[1] = "Shadow Strike"
    }

    override fun descriptChar(){
        println("A master of shadows and deception, the Thief strikes swiftly and vanishes without a trace.")
        println("Level: $level  Hp: $hp  Attack: $attack  Defense: $defense")
    }

    override fun listSkills(){
        for((index, name) in skillNames){
            println("$index. $name\n")
        }
    }
    // Takes skillid and enemy as input and casts related skill to enemy
    override fun useSkillById(skillId: Int, enemy: Combatant): Int{
        val skill = skillMap[skillId]
        return skill!!.invoke(enemy)
    }

    override fun calculateDamage(enemy: Combatant): Int {
        var multiplier = 1
        if(Random.Default.nextDouble(0.0, 1.0) > 0.65){
            multiplier = 2
        }
        return ((attack) - (enemy.defense / 10)) * multiplier
    }
    // Thief's standard skill
    fun shadowStrike(enemy: Combatant): Int{
        var damage: Int

        damage = calculateDamage(enemy)
        println("${if (damage > attack) "[CRIT!]" else ""} ${name} strikes from the shadows and deals $damage damage!")

        return damage
    }

}