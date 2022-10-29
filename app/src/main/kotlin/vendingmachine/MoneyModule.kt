package vendingmachine

import vendingmachine.model.Money

object MoneyModule {
    fun calculateAmount(moneyMap: Map<Money, Int>): Int {
        return moneyMap.entries.sumOf { (money, num) -> money.value * num }
    }

    fun toMoneyCount(amount: Int, supportedMoneySet: Set<Money>): Map<Money, Int> {
        var rest = amount
        val moneyCount = mutableMapOf<Money, Int>()
        for (money in supportedMoneySet.sortedBy { -it.value }) {
            val count = rest / money.value;
            rest %= money.value
            moneyCount[money] = count
        }
        if (rest != 0) {
            throw IllegalArgumentException()
        }
        return moneyCount.toMap()
    }
}
