package src.model.characterType

interface Combatant{
    val name: String
    var level: Int
    var hp: Int
    var defense: Int
    var attack: Int
    var skillMap: MutableMap<Int, (Combatant) -> Int>
    var skillNames: MutableMap<Int, String>

    fun descriptChar()
    fun listSkills()
}
