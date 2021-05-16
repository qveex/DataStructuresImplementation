package dataStructures

import java.security.MessageDigest
import kotlin.collections.HashSet
import kotlin.math.abs

// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/
class MyHashMap<K, V> :  MutableMap<K, V> {

    private class Node<K, V>(override val key: K, override var value: V) : MutableMap.MutableEntry<K, V> {
        override fun setValue(newValue: V): V {
            val oldValue = value
            value = newValue
            return oldValue
        }

        override fun toString(): String { return "$key = $value" }
    }

    private var table = arrayOfNulls<Node<K, V>>(16)
    private var elements = 0
    override val size: Int
        get() = table.size

    override fun containsKey(key: K): Boolean {
        return table[doubleHash(key)] != null
    }

    override fun containsValue(value: V): Boolean {
        table.forEach{ if (it?.value == value) return true }
        return false
    }

    override fun get(key: K): V? {
        val hash = find(key)
        return if (hash != null) table[hash % size]?.value
        else null
    }

    override fun isEmpty() = elements == 0
    fun isNotEmpty() = elements != 0

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() {
            val set = HashSet<Node<K, V>>()
            table.forEach { if (it != null) set.add(it) }
            return set.toMutableSet()
        }

    override val keys: MutableSet<K>
        get() {
            val set = HashSet<K>()
            table.forEach { if (it != null) set.add(it.key) }
            return set
        }

    override val values: MutableCollection<V>
        get() {
            val set = HashSet<V>()
            table.forEach { if (it != null) set.add(it.value) }
            return set
        }

    override fun clear() {
        table = arrayOfNulls(10)
        elements = 0
    }

    override fun put(key: K, value: V): V? {
        val hash = doubleHash(key)
        val oldElement = table[hash % size]?.value
        resize()
        table[hash % size] = Node(key, value)
        elements++
        return oldElement
    }

    override fun putAll(from: Map<out K, V>) {
        from.forEach{ put(it.key, it.value) }
    }

    override fun remove(key: K): V? {
        var oldElement: V? = null
        val hash = find(key)
        if (hash != null) {
            oldElement = table[hash % size]?.value
            table[hash % size] = null
            elements--
        }

        return oldElement
    }

    private fun hashCode2(key: K): Int {
        return MessageDigest
            .getInstance("SHA-256")
            .digest(key.toString().toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }.hashCode()
    }

    private fun resize() {
        if ((elements.toDouble() / size) * 100 >= 75) {
            val newTable = arrayOfNulls<Node<K, V>>(size * 2)
            table.forEach {
                if (it != null)
                    newTable[doubleHash(it.key) % newTable.size] = it
            }
            table = newTable
        }
    }

    private fun doubleHash(key: K): Int {
        var hash = abs(key.hashCode())
        var num = 0
        while (table[hash % size] != null && table[hash % size]?.key != key) {
            num++
            hash = abs(key.hashCode() + 3 * num + 2 * num * num)
            if (hash > size) hash %= size
        }
        return hash
    }

    private fun find(key: K): Int? {
        var hash = abs(key.hashCode())
        var num = 0
        while (table[hash % size]?.key != key) {
            num++
            hash = abs(key.hashCode() + 3 * num + 2 * num * num)
            if (hash > size) hash %= size
            if (num == size / 2) return null
        }
        return hash
    }
}