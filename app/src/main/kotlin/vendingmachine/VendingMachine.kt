package vendingmachine

import vendingmachine.model.ItemInformation
import vendingmachine.model.Money

class VendingMachine(
    private var insertedMoneyAmount: Int,
    private val itemInformation: ItemInformation
) {
    constructor() : this(0, ItemInformation(name = "コーラ", price = 120, stock = 5))

    fun insert(money: Money): Money? {
        if (supportedMoneySet.contains(money)) {
            insertedMoneyAmount += money.value
            return null
        }

        return money
    }

    fun getAmount(): Int {
        return insertedMoneyAmount
    }

    fun returnMoney(): Map<Money, Int> {
        val currentAmount = insertedMoneyAmount
        insertedMoneyAmount = 0
        return MoneyModule.toMoneyCount(currentAmount, supportedMoneySet)
    }

    fun getItemInformation(): ItemInformation {
        return itemInformation
    }

    companion object {
        private val supportedMoneySet = setOf(Money.Ten, Money.Fifty, Money.Hundred, Money.FiveHundred, Money.Thousand)
    }
}
