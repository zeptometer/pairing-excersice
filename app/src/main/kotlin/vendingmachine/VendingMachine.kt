package vendingmachine

class VendingMachine(private var moneyCount: MutableMap<Money, Int>) {
    constructor() : this(initializeMoneyCount())

    fun insert(money: Money) {
        val current = moneyCount[money]!!
        moneyCount[money] = current + 1
    }

    fun getAmount(): Int {
        return Money.values().sumOf { moneyCount[it]!! * it.value  }
    }

    fun returnMoney(): Map<Money, Int> {
        val currentMap = moneyCount
        moneyCount = initializeMoneyCount()
        return currentMap
    }
}

fun initializeMoneyCount(): MutableMap<Money, Int> {
    return mutableMapOf(
        Money.Ten to 0,
        Money.Fifty to 0,
        Money.Hundred to 0,
        Money.FiveHundred to 0,
        Money.Thousand to 0,
    )
}