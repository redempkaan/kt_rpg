package src.model.specs

import src.model.characterType.CharacterType
import src.model.characterType.Combatant
import kotlin.collections.iterator
import kotlin.random.Random

class Mage (override val name: String, type: CharacterType = CharacterType.MAGE): Combatant {
    override var level = 1
    override var hp = type.baseHp
    override var attack = type.baseAtk
    override var defense = type.baseDefense
    override var skillMap = mutableMapOf<Int, (Combatant) -> Int>()
    override var skillNames = mutableMapOf<Int, String>()

    init{
        skillMap[1] = ::fireBall
        skillNames[1] = "Fireball"
    }

    override fun descriptChar(){
        println("A master of arcane arts, the Mage bends the elements to unleash devastating spells, trading resilience for unrivaled magical power.")
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
        if(Random.Default.nextDouble(0.0, 1.0) > 0.8){
            multiplier = 2
        }
        return (attack - (enemy.defense / 10)) * multiplier
    }

    fun fireBall(enemy: Combatant): Int{
        val damage = calculateDamage(enemy)

        println("\n${if (damage > attack) "[CRIT!]" else ""}$name casts a fireball ${enemy.name} dealing ${damage} damage!")

        return damage
    }

}