package com.fups.model

abstract class Character(val name: String){

    abstract var level: Int
    abstract var hp: Int
    abstract var mana: Int
    abstract var damage: Int
    abstract var attackSpeed: Double
    abstract var movementSpeed: Double
    abstract var dodgeChance: Double
    abstract var critRate: Double

    abstract fun hit(enemy: Character)

    fun levelUp(){
        level += 1
        hp += hp * 10 / 100
        mana += mana * 5 / 100
        damage += damage * 3 / 100
        attackSpeed += 0.5
        movementSpeed += 0.5
        dodgeChance += 0.25
        critRate += 0.25

    }

}

class Warrior(name: String): Character(name){
    override var level: Int = 1
    override var hp: Int = 200
    override var mana: Int = 50
    override var damage: Int = 10
    override var attackSpeed: Double = 2.0
    override var movementSpeed: Double = 5.0
    override var dodgeChance: Double = 1.0
    override var critRate: Double = 5.0


    override fun hit(enemy: Character){
        val hitMissChance = enemy.dodgeChance + (enemy.movementSpeed * 10 / 100)
        println("Hit Miss Chance: $hitMissChance")

    }
}
