package src.model.specs

import src.model.characterType.CharacterType
import src.model.characterType.Combatant
import kotlin.collections.iterator
import kotlin.random.Random

class Ranger (override val name: String, type: CharacterType = CharacterType.RANGER): Combatant {
    override var level = 1
    override var hp = type.baseHp
    override var attack = type.baseAtk
    override var defense = type.baseDefense
    override var skillMap = mutableMapOf<Int, (Combatant) -> Int>()
    override var skillNames = mutableMapOf<Int, String>()
    // Adding skill function references and names to related lists
    init{
        skillMap[1] = ::multiShot
        skillNames[1] = "Multishot"
    }

    override fun descriptChar(){
        println("Silent as the wind and swift as the shadows, the Ranger strikes from afar with deadly precision.")
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
        if(Random.Default.nextDouble(0.0, 1.0) > 0.9){
            multiplier = 2
        }
        return ((attack / 3) - (enemy.defense / 10)) * multiplier
    }
    // Ranger's standard skill
    fun multiShot(enemy: Combatant): Int{
        var index: Int = 0
        var damage: Int = 0
        var tempDamage: Int = 0

        println("${name} is casting Multishot at ${enemy.name}\n")

        while(index < 3){
            damage += calculateDamage(enemy)
            tempDamage = damage - tempDamage

            println("${if (damage > attack / 3) "[CRIT!]" else ""} ${index + 1}. shot dealed $tempDamage damage!")
            index++
        }
        return damage
    }

}