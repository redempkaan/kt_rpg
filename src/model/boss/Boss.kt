package src.model.boss

import src.model.bossType.BossType
import src.model.characterType.Combatant

class Boss(override val name: String, val type: BossType): Combatant {
    override var level: Int = type.level
    override var hp: Int = type.hp
    override var attack: Int = type.attack
    override var defense: Int = type.defense
    override var skillMap = mutableMapOf<Int, (Combatant) -> Int>()
    override var skillNames = mutableMapOf<Int, String>()
    // Adding skill function references and names to related lists
    init{
        skillMap[1] = ::strike
        skillNames[1] = "Strike"
    }

    override fun descriptChar(){
        println("$type BOSS")
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

    override fun calculateDamage(enemy: Combatant): Int{
        return (attack - (enemy.defense * 10 / 100))
    }
    //Bosses' standard skill (uses calculateDamage function)
    fun strike(enemy: Combatant): Int{
        val damage = calculateDamage(enemy)
        println("$name STRIKES AT ${enemy.name} DEALING $damage DAMAGE!")
        return damage
    }
}