package com.fups.model

import kotlin.random.Random

abstract class Character(val name: String){

    private var _id: Int = nextId()
     val id: Int
         get() = _id

    abstract var level: Int
    abstract var hp: Int
    abstract var damage: Int
    abstract var attackSpeed: Double
    abstract var movementSpeed: Double
    abstract var dodgeChance: Double
    abstract var critRate: Double

    abstract fun hit(enemy: Character): Int
    abstract fun copy(): Character

    protected fun setId(newId: Int) {
        _id = newId
    }


    fun levelUp(){
        level += 1
        hp += hp * 10 / 100
        damage += damage * 3 / 100
        attackSpeed += attackSpeed * 5 / 100
        if(movementSpeed < 0.5){
            movementSpeed += movementSpeed * 100 / 5
        }
        if(dodgeChance < 0.5){
            dodgeChance += dodgeChance * 100 / 5
        }
        if(critRate < 0.5){
            critRate += critRate * 100 / 5
        }
        println("$name has leveled up to $level!")
        return

    }

    fun calculateMissChance(): Double {
        return (0.7 * dodgeChance) + (0.3 * movementSpeed)
    }

    companion object{
        private var counter = 1

        private fun nextId(): Int{
            return counter++
        }
    }
}

    class Warrior(name: String): Character(name){


    override var level: Int = 1
    override var hp: Int = 200
    override var damage: Int = 10
    override var attackSpeed: Double = 0.2
    override var movementSpeed: Double = 0.1
    override var dodgeChance: Double = 0.02
    override var critRate: Double = 0.1

        override fun copy(): Character {
            val tempWarrior = Warrior(name)
            tempWarrior.setId(id)
            return tempWarrior
        }


    override fun hit(enemy: Character): Int{
        if(!(Random.nextDouble() < calculateMissChance())){
            if(Random.nextDouble() < critRate){
                println("${name.uppercase()} SHATTERED ${enemy.name.uppercase()}'s FLESH WITH THE GIANT AXE DEALING ${enemy.damage * 2} DAMAGE, BLOOD EVERYWHERE!!")
                return enemy.damage * 2
            }
            println("$name swings the giant axe to ${enemy.name} DEALING ${enemy.damage} damage!")
            return enemy.damage
        }
        println("Oh no! $name totally missed!")
        return 0

    }
}
