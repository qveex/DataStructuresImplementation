import data.SIMInfo
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


fun treeTest() {
    var begin: Long = System.currentTimeMillis()
    val tree = MyTreeMap<String, String>()
    for (i in 0 until 10000)
        tree.put(generateString(), generateString())

    tree.forEach { tree.remove(it.value) }

    var end: Long = System.currentTimeMillis()
    println("[MY TREE] Time spent: ${ end - begin } ms")



    begin = System.currentTimeMillis()
    val tree2 = TreeMap<String, String>()
    for (i in 0 until 10000)
        tree2.put(generateString(), generateString())

    tree2.forEach { tree2.remove(it.value) }

    end = System.currentTimeMillis()
    println("[JAVA TREE] Time spent: ${ end - begin } ms\n")
}
fun tableTest() {
    var begin: Long = System.currentTimeMillis()

    val map = MyHashMap<String, String>()
    for (i in 0 until 10000)
        map.put(generateString(), generateString())

    var end: Long = System.currentTimeMillis()
    println("[MY TABLE] Time spent: ${ end - begin } ms")



    begin = System.currentTimeMillis()

    val map2 = HashMap<String, String>()
    for (i in 0 until 10000)
        map2.put(generateString(), generateString())

    end = System.currentTimeMillis()
    println("[JAVA TABLE] Time spent: ${ end - begin } ms\n")
}
fun listTest() {
    val list = MyList<SIMInfo>()

    list.add(SIMInfo("1", "-111","0","0"))
    list.add(SIMInfo("1", "1","0","0"))
    list.add(SIMInfo("1", "3","0","0"))
    list.add(SIMInfo("1", "2","0","0"))
    list.add(SIMInfo("1", "123","0","0"))
    list.add(SIMInfo("1", "7","0","0"))
    list.add(SIMInfo("1", "123","0","0"))
    list.add(SIMInfo("1", "-22222","0","0"))
    list.sort(list)
}

fun TEST() {
    println("Tree test time: ")
    treeTest()
    println("Table test time: ")
    tableTest()
}


fun main() {

    val list = MyList<Int>()

    list.add(1)
    list.add(2)
    list.add(3)
    list.add(4)
    list.add(5)
    list.add(6)

    list.remove(2)
    list.remove(266)
    list.remove(6)
    list.remove(1)

    list.add(5)
    list.add(55)
    list.remove(5)
    list.remove(55)
    list.add(0, 1)
    list.add(2, 1)
    list.add(list.size - 1, 6)
    list.add(1, 7)
    list.add(0, 8)
    list.add(list.size - 2, 8)

}