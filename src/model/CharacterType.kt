package src.model.characterType


enum class CharacterType(val baseHp: Int, val baseAtk: Int, val baseDefense: Int){
    WARRIOR(200, 45, 20),
    MAGE(120, 60, 10),
    ARCHER(150, 50, 15),
    THIEF(140, 55, 12)
}



