package src.model.bossType
//Different base level, hp, attack, defense values for bosses
enum class BossType(val level: Int, val hp: Int, val attack: Int, val defense: Int) {
    NORMAL(5, 250, 35, 20),
    ELITE(20, 525, 58, 40),
    LEGENDARY(50, 800, 80, 80),
    MYTHIC(100, 1500, 120, 100)
}