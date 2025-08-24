package com.fups.model

import kotlin.random.Random

class Arena{
    fun startDuel(c1: Character, c2: Character) {

        val first: Character
        val second: Character
        val winnerId: Int
        var roundCounter: Int = 1
        var dealedDamage: Int

        if(Random.nextBoolean()){
            first = c1.copy()
            second = c2.copy()
        }
        else{
            first = c2.copy()
            second = c1.copy()
        }

        println("${first.name.uppercase()} TAKES THE ADVANTAGE!")

        while((first.hp > 0) && (second.hp > 0)){
            if(roundCounter % 2 == 1){
                dealedDamage = first.hit(second)
                second.hp -= dealedDamage
            }
            else{
                dealedDamage = second.hit(first)
                first.hp -= dealedDamage
            }
            println("${first.name} : ${first.hp},  ${second.name} : ${second.hp}")
            roundCounter++
        }
        if(first.hp <= 0){
            println("${second.name} HAS WON THE DUEL!")
            winnerId = second.id

        }
        else{
            println("${first.name} HAS WON THE DUEL!")
            winnerId = first.id
        }
        println("${winnerId} / ${c1.name} ${c1.id} / ${c2.name} ${c2.id}")
        if(winnerId == c1.id){
            c1.levelUp()
        }
        else{
            c2.levelUp()
        }
        return
    }



}