import dataStructures.*
import java.util.*
import kotlin.random.Random

private fun generateString(): String {
    var line = ""
    val chars = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMйцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ"
    val lengthStr: Int = Random.nextInt(5, 25)
    for (i in 0 until lengthStr) line += chars[Random.nextInt(0, chars.length)]
    return line
}

fun TEST() {
    var begin: Long = System.currentTimeMillis()

    /*val map = MyHashMap<String, String>()
    for (i in 0 until 10000)
        map.put(generateString(), generateString())*/

    val tree = MyTreeMap<String, String>()
    for (i in 0 until 10000)
        tree.put(generateString(), generateString())

    var end: Long = System.currentTimeMillis()
    println("!Time spent: ${ end - begin } ms\n")

    begin = System.currentTimeMillis()


    /*val map2 = HashMap<String, String>()
    for (i in 0 until 10000)
        map2.put(generateString(), generateString())*/

    val tree2 = TreeMap<String, String>()
    for (i in 0 until 10000)
        tree2.put(generateString(), generateString())

    end = System.currentTimeMillis()
    println("!Time spent: ${ end - begin } ms\n")
}

fun main() {
    val tree = MyTreeMap<Int, String>()
    tree.put(1, "Misha")
    tree.put(2, "Alexandr")
    tree.put(3, "Rakhim")
    tree.put(7, "Serega")
    tree.put(1, "LOX")
    tree.put(1, "ne LOX")
    tree.put(5, "lalala")
    tree.put(-2, "ye")
    tree.put(-1, "yep")
    tree.put(-3, "yepa")
    tree.print()

    println(tree.values)
    println("containsKey = ${tree.containsKey(2)}")
    println("containsValue = ${tree.containsValue("Alexandr")}")
    tree.remove(2)
    println("containsKey = ${tree.containsKey(2)}")
    println()
    println("containsValue = ${tree.containsValue("Alexandr")}")
    println("containsValue = ${tree.containsValue("lalala")}")
    println("containsValue = ${tree.containsValue("Serega")}")
    println("containsValue = ${tree.containsValue("hnjiosdfjigjsdh")}")
    tree.remove(7)
    println("containsValue = ${tree.containsValue("Serega")}")
    println(tree.values)
    tree.print()

}