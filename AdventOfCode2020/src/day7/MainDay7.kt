package day7

val lineRegex = Regex("(\\d)* ?(\\w* \\w*) bags?")
val myBag = "shiny gold"

fun main() {
    val fileContent: String = {}::class.java.getResource("/day7.txt").readText().trim()

    println("Puzzle 1 : " + containedBy(myBag, getContaineeMap(fileContent, lineRegex)).size)
    println("Puzzle 2 : " + contains(myBag, getContainerMap(fileContent, lineRegex)))
}

fun getContaineeMap(input: String, regex: Regex): Map<String,Set<String>> {
    return input.lineSequence()
         .fold(HashMap<String, Set<String>>()) { map, line ->
             val colorList = regex.findAll(line).map { matchGroup -> matchGroup.groupValues[2] }.toList()
             val container = colorList[0]
             val mergeableSet = HashSet<String>()
             mergeableSet.add(container)
             val contained = colorList.subList(1,colorList.size)
             contained.onEach { map.merge(it, mergeableSet) { set1, set2 -> set1.plus(set2) } }
             map
         }
}

fun containedBy(bag: String, bagTree: Map<String,Set<String>>, containers:Set<String> = HashSet()):Set<String> {
    val root = bagTree[bag]
    if (root.isNullOrEmpty()) {
        return containers + bag
    } else {
        return root.map { bag -> containedBy(bag, bagTree, root+ containers) }.reduce { acc, set -> acc + set }
    }
}

fun getContainerMap(input: String, regex: Regex): Map<String,Map<String,Int>> {
    return input.lineSequence()
            .fold(HashMap<String, Map<String,Int>>()) { map, line ->
                val colorMaps = regex.findAll(line).fold(HashMap<String,Int>()) { countMap, matchGroup ->
                        countMap[matchGroup.groupValues[2]] = if(matchGroup.groupValues[1].toIntOrNull()== null) 0 else matchGroup.groupValues[1].toInt()
                        countMap
                }
                val container = colorMaps.filterValues { it == 0 }.keys.first()
                colorMaps.remove(container)
                map[container] = colorMaps
                map
            }
}

fun contains(bag: String, bagTree: Map<String,Map<String,Int>>): Int {
    val branch = bagTree[bag]
    if (branch.isNullOrEmpty()) {
        return 0
    } else {
        return branch.keys.map { key -> branch[key]!! * (1 + contains(key, bagTree)) }.sum()
    }
}