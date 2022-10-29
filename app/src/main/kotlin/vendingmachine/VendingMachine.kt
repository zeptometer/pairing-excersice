package vendingmachine

import vendingmachine.model.ItemInformation

class VendingMachine(
    private var moneyCount: MutableMap<Money, Int>,
    private val itemInformation: ItemInformation
) {
    constructor() : this(initializeMoneyCount(), ItemInformation(name = "コーラ", price = 120, stock = 5))

    fun insert(money: Money): Money? {
        if (supportedMoneySet.contains(money)) {
            val current = moneyCount[money] ?: 0
            moneyCount[money] = current + 1
            return null
        }

        return money
    }

    fun getAmount(): Int {
        return supportedMoneySet.sumOf { (moneyCount[it] ?: 0) * it.value }
    }

    fun returnMoney(): Map<Money, Int> {
        val currentMap = moneyCount
        moneyCount = initializeMoneyCount()
        return currentMap
    }

    fun getItemInformation(): ItemInformation {
        return itemInformation
    }

    companion object {
        private val supportedMoneySet = setOf(Money.Ten, Money.Fifty, Money.Hundred, Money.FiveHundred, Money.Thousand)

        private fun initializeMoneyCount(): MutableMap<Money, Int> {
            return supportedMoneySet.associateWith { 0 }.toMutableMap()
        }
    }
}
