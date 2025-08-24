import com.fups.model.Warrior
import com.fups.model.Arena


fun main(){

    val arena = Arena()

    val warrior1 = Warrior("Bert")
    val warrior2 = Warrior("Makan")


    arena.startDuel(warrior1, warrior2)


}