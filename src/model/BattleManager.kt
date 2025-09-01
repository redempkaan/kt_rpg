package src.model.battleManager

import src.model.characterType.Combatant

class BattleManager() {

    fun pickCombatants(combatantList: List<Combatant>): Pair<Combatant, Combatant> {
        var indexC1: Int
        var indexC2: Int
        var input: Int
        //Listing combatant with their descriptions for user to select combatants
        combatantList.forEachIndexed {index, combatant ->
            println("\n${index + 1}) ${combatant.name}")
            combatant.descriptChar()
        }

        indexC1 = -1

        println("\nEnter the id of the character to play as (1 - ${combatantList.size})")
        while(indexC1 == -1) {
            try { // Checking input exception
                input = readln().toInt()
                combatantList[input - 1]
                indexC1 = input - 1
            }
            catch (e: Exception) {
                when(e) {
                    is IndexOutOfBoundsException -> println("Input value must be between 1 and ${combatantList.size}")
                    is NumberFormatException -> println("The input value must be a number")
                }
            }

        }


        combatantList.forEachIndexed {index, combatant ->
            println("\n${index + 1}) ${combatant.name}")
            combatant.descriptChar()
        }

        indexC2 = -1
        println("\nEnter the id of the opponent to fight")

        while(indexC2 == -1) {
            try { // Checking input exception
                input = readln().toInt()
                combatantList[input - 1]
                indexC2 = input - 1
            }
            catch (e: Exception) {
                when(e) {
                    is IndexOutOfBoundsException -> println("Input value must be between 1 and ${combatantList.size}")
                    is NumberFormatException -> println("The input value must be a number")
                }
            }

        }

        // Returns picked combatants
        return Pair(combatantList[indexC1], combatantList[indexC2])


    }

    fun duel(combatant1 : Combatant, combatant2 : Combatant) {
        var tempHp1: Int = combatant1.hp
        var tempHp2: Int = combatant2.hp
        var tempDefense1: Int = combatant1.defense
        var tempDefense2: Int = combatant2.defense
        var tempAttack1: Int = combatant1.attack
        var tempAttack2: Int = combatant2.attack
        var dealedDamage: Int
        var roundCounter: Int = 1
        var skillId: Int
        // Combatants take turn by order and uses the selected skill (enemy automatically casts its first skill)
        println("THE DUEL BETWEEN ${combatant1.name} AND ${combatant2.name} BEGINS!")
        while((tempHp1 > 0) && (tempHp2 > 0)){
            if(roundCounter % 2 == 1){
                println("\nThe turn is yours!")
                combatant1.listSkills()
                println("Enter the skill's id that you want to use")

                dealedDamage = -1
                while(dealedDamage == -1){
                    try { //Checking input exception
                        skillId = readln().toInt()
                        dealedDamage = combatant1.useSkillById(skillId, combatant2)
                    }
                    catch (e: Exception) {
                        when (e) {
                            is NullPointerException -> println("Invalid skill ID, please look up for your skill list")
                            is NumberFormatException -> println("The skill ID must be a number")
                        }
                    }
                }
                tempHp2 -= dealedDamage
            }
            else {
                dealedDamage = combatant2.useSkillById(1, combatant1)
                tempHp1 -= dealedDamage
            }

            println("\nTurn ${roundCounter}:  ${combatant1.name} : ${tempHp1},  ${combatant2.name} : ${tempHp2}\n")
            roundCounter++
        }

        if(tempHp1 <= 0){
            println("${combatant2.name} HAS WON THE DUEL!")

        }
        else{
            println("${combatant1.name} HAS WON THE DUEL!")
        }

        combatant1.hp = tempHp1
        combatant1.defense = tempDefense1
        combatant1.attack = tempAttack1
        combatant2.hp = tempHp2
        combatant2.defense = tempDefense2
        combatant2.attack = tempAttack2

        return
    }

}

