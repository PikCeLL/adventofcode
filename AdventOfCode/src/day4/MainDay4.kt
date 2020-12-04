package day4


val mandatoryFields: List<String> = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
val optionalFields: List<String> = listOf("cid")

fun main() {
    val fileContent: String = {}::class.java.getResource("/day4.txt").readText()

    val splitContent: List<String> = fileContent.split("\n\n")
    val regex = Regex(generateRegex(mandatoryFields, optionalFields))

    println("Puzzle 1 : " + listValidPassportsRule1(splitContent, regex, mandatoryFields, optionalFields).size)
    println("Puzzle 2 : " + listValidPassportsRule2(splitContent, regex, mandatoryFields, optionalFields).size)
}

fun generateRegex(mandatoryFields: List<String>, optionalFileds: List<String>): String {
    val fields = mandatoryFields + optionalFileds
    return fields.fold("(") { regex, field -> regex + when {
            regex == "(" -> ""
            else -> "|"
        } + field + ":(?<" + field + ">[\\#0-z]+)"
    } + ")"
}

fun buildPassportDatabase(passportList:List<String>, regex:Regex, fields: List<String>): List<HashMap<String?, MutableList<String>>> {
    return passportList.map { passport -> regex.findAll(passport) }.map { resGroupCollection -> resGroupCollection.groupByTo(HashMap(), { group -> fields.find { (group.groups as MatchNamedGroupCollection)[it]!=null } }, {group -> fields.fold("") { res, field -> res+if((group.groups as MatchNamedGroupCollection)[field]!=null) (group.groups as MatchNamedGroupCollection)[field]!!.value else ""} }) }
}

fun listValidPassportsRule1(passportList:List<String>, regex:Regex, mandatoryFields: List<String>, optionalFields: List<String>): List<HashMap<String?, MutableList<String>>> {
    val fields = mandatoryFields + optionalFields
    return buildPassportDatabase(passportList, regex, fields).filter { map -> mandatoryFields.all { map.containsKey(it) } }
}

fun listValidPassportsRule2(passportList:List<String>, regex:Regex, mandatoryFields: List<String>, optionalFields: List<String>): List<HashMap<String?, MutableList<String>>> {
    return listValidPassportsRule1(passportList,regex,mandatoryFields, optionalFields).filter { map -> mandatoryFields.all { when(it) {
        "byr" -> IntRange(1920,2002).contains(map[it]?.single()?.toInt())
        "iyr" -> IntRange(2010,2020).contains(map[it]?.single()?.toInt())
        "eyr" -> IntRange(2020,2030).contains(map[it]?.single()?.toInt())
        "hgt" -> when {
                    map[it]?.single()?.endsWith("in")!! -> IntRange(59,76).contains(map[it]?.single()?.removeSuffix("in")?.toInt())
                    map[it]?.single()?.endsWith("cm")!! -> IntRange(150,193).contains(map[it]?.single()?.removeSuffix("cm")?.toInt())
                    else -> false
                }
        "hcl" -> Regex("\\#[0-9a-f]{6}").matches(map[it]?.single()!!)
        "ecl" -> listOf("amb","blu", "brn", "gry", "grn", "hzl", "oth").contains(map[it]?.single()!!)
        "pid" -> Regex("[0-9]{9}").matches(map[it]?.single()!!)
        else -> false
    } } }
}