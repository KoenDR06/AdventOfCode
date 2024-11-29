package me.koendev.year2023

import me.koendev.println
import me.koendev.getInput

fun main() {
    part1(getInput(2023, 7)).println()
    part2(getInput(2023, 7)).println()
}

private fun part1(input: List<String>) : Int {
    val fiveOfAKinds = mutableListOf<Pair<String, Int>>()
    val fourOfAKinds = mutableListOf<Pair<String, Int>>()
    val fullHouses = mutableListOf<Pair<String, Int>>()
    val threeOfAKinds = mutableListOf<Pair<String, Int>>()
    val twoPairs = mutableListOf<Pair<String, Int>>()
    val onePairs = mutableListOf<Pair<String, Int>>()
    val highCards = mutableListOf<Pair<String, Int>>()

    for (line in input) {
        val splitHand = line.split(" ")
        val bid = splitHand[1].toInt()
        var hand = splitHand[0]

        hand = hand
            .replace('A', 'a')
            .replace('K', 'b')
            .replace('Q', 'c')
            .replace('J', 'd')
            .replace('T', 'e')
            .replace('9', 'f')
            .replace('8', 'g')
            .replace('7', 'h')
            .replace('6', 'i')
            .replace('5', 'j')
            .replace('4', 'k')
            .replace('3', 'l')
            .replace('2', 'm')

        val charMap = mutableMapOf<Char, Int>()
        for (char in hand) {
            var charCount = charMap[char]
            if (charCount == null) {
                charCount = 1
            } else {
                charCount++
            }
            charMap[char] = charCount
        }

        if (charMap.values.max() == 5) {
            fiveOfAKinds.add(Pair(hand, bid))
        } else if (charMap.values.max() == 4) {
            fourOfAKinds.add(Pair(hand, bid))
        } else if (charMap.values.max() == 3) {
            if (charMap.count() == 2) {
                fullHouses.add(Pair(hand, bid))
            } else if (charMap.count() == 3) {
                threeOfAKinds.add(Pair(hand, bid))
            }
        } else if (charMap.values.max() == 2) {
            if (charMap.count() == 3) {
                twoPairs.add(Pair(hand, bid))
            } else if (charMap.count() == 4) {
                onePairs.add(Pair(hand, bid))
            }
        } else {
            highCards.add(Pair(hand, bid))
        }
    }

    var winnings = 0
    var rank = 1

    fiveOfAKinds.sortByDescending { it.first }
    fourOfAKinds.sortByDescending { it.first }
    fullHouses.sortByDescending { it.first }
    threeOfAKinds.sortByDescending { it.first }
    twoPairs.sortByDescending { it.first }
    onePairs.sortByDescending { it.first }
    highCards.sortByDescending { it.first }

//    highCards.println()
//    onePairs.println()
//    twoPairs.println()
//    threeOfAKinds.println()
//    fullHouses.println()
//    fourOfAKinds.println()
//    fiveOfAKinds.println()

    for (hand in highCards) winnings += hand.second * rank++
    for (hand in onePairs) winnings += hand.second * rank++
    for (hand in twoPairs) winnings += hand.second * rank++
    for (hand in threeOfAKinds) winnings += hand.second * rank++
    for (hand in fullHouses) winnings += hand.second * rank++
    for (hand in fourOfAKinds) winnings += hand.second * rank++
    for (hand in fiveOfAKinds) winnings += hand.second * rank++

    return winnings
}

private enum class HandRank {
    FIVE,
    FOUR,
    FULL,
    THREE,
    TWO_PAIR,
    ONE_PAIR,
    HIGH
}

private fun analyseHand(charMap: MutableMap<Char, Int>) : HandRank {
    val jokers: Int = if (charMap['z'] == null) 0 else charMap['z']!!
    charMap.remove('z')
    if (charMap.count() <= 1) {
        return HandRank.FIVE
    }
    else if (charMap.values.max() == 4) {
        when (jokers) {
            0 -> return HandRank.FOUR
            1 -> return HandRank.FIVE
        }
    }
    else if (charMap.values.max() == 3) {
        if (charMap.count() == 2) {
            when (jokers) {
                0 -> return HandRank.FULL
                1 -> return HandRank.FOUR
            }
        } else if (charMap.count() == 3) {
            return HandRank.THREE
        }
    }
    else if (charMap.values.max() == 2) {
        if (charMap.count() == 2) {
            when (jokers) {
                1 -> return HandRank.FULL
                2 -> return HandRank.FOUR
            }
        } else if (charMap.count() == 3) {
            when (jokers) {
                0 -> return HandRank.TWO_PAIR
                1 -> return HandRank.THREE
            }
        } else if (charMap.count() == 4) {
            return HandRank.ONE_PAIR
        }
    }
    else if (charMap.values.max() == 1) {
        when (charMap.count()) {
            2 -> return HandRank.FOUR
            3 -> return HandRank.THREE
            4 -> return HandRank.ONE_PAIR
            5 -> return HandRank.HIGH
        }
    }
    throw Exception("Unreachable")
}

private fun part2(input: List<String>) : Int {
    val fiveOfAKinds = mutableListOf<Pair<String, Int>>()
    val fourOfAKinds = mutableListOf<Pair<String, Int>>()
    val fullHouses = mutableListOf<Pair<String, Int>>()
    val threeOfAKinds = mutableListOf<Pair<String, Int>>()
    val twoPairs = mutableListOf<Pair<String, Int>>()
    val onePairs = mutableListOf<Pair<String, Int>>()
    val highCards = mutableListOf<Pair<String, Int>>()

    for (line in input) {
        val splitHand = line.split(" ")
        val bid = splitHand[1].toInt()
        var hand = splitHand[0]

        hand = hand
            .replace('A', 'a')
            .replace('K', 'b')
            .replace('Q', 'c')
            .replace('T', 'e')
            .replace('9', 'f')
            .replace('8', 'g')
            .replace('7', 'h')
            .replace('6', 'i')
            .replace('5', 'j')
            .replace('4', 'k')
            .replace('3', 'l')
            .replace('2', 'm')
            .replace('J', 'z')

        val charMap = mutableMapOf<Char, Int>()
        for (char in hand) {
            var charCount = charMap[char]
            if (charCount == null) {
                charCount = 1
            } else {
                charCount++
            }
            charMap[char] = charCount
        }

        when (analyseHand(charMap)) {
            HandRank.FIVE -> fiveOfAKinds.add(Pair(hand, bid))
            HandRank.FOUR -> fourOfAKinds.add(Pair(hand, bid))
            HandRank.FULL -> fullHouses.add(Pair(hand, bid))
            HandRank.THREE -> threeOfAKinds.add(Pair(hand, bid))
            HandRank.TWO_PAIR -> twoPairs.add(Pair(hand, bid))
            HandRank.ONE_PAIR -> onePairs.add(Pair(hand, bid))
            HandRank.HIGH -> highCards.add(Pair(hand, bid))
        }
    }

    var winnings = 0
    var rank = 1

    fiveOfAKinds.sortByDescending { it.first }
    fourOfAKinds.sortByDescending { it.first }
    fullHouses.sortByDescending { it.first }
    threeOfAKinds.sortByDescending { it.first }
    twoPairs.sortByDescending { it.first }
    onePairs.sortByDescending { it.first }
    highCards.sortByDescending { it.first }

//    highCards.println()
//    onePairs.println()
//    twoPairs.println()
//    threeOfAKinds.println()
//    fullHouses.println()
//    fourOfAKinds.println()
//    fiveOfAKinds.println()

    for (hand in highCards) winnings += hand.second * rank++
    for (hand in onePairs) winnings += hand.second * rank++
    for (hand in twoPairs) winnings += hand.second * rank++
    for (hand in threeOfAKinds) winnings += hand.second * rank++
    for (hand in fullHouses) winnings += hand.second * rank++
    for (hand in fourOfAKinds) winnings += hand.second * rank++
    for (hand in fiveOfAKinds) winnings += hand.second * rank++

    return winnings
}