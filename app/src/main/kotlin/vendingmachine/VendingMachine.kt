package vendingmachine

import vendingmachine.model.ItemInformation
import vendingmachine.model.Money

class VendingMachine(
    private var insertedMoneyAmount: Int,
    private var soldAmount: Int,
    private val itemInformations: MutableSet<ItemInformation>
) {
    constructor() : this(0, 0, DEFAULT_ITEM_INFOS.toMutableSet())

    fun insert(money: Money): Money? {
        if (supportedMoneySet.contains(money)) {
            insertedMoneyAmount += money.value
            return null
        }

        return money
    }

    fun getInsertedAmount(): Int {
        return insertedMoneyAmount
    }

    fun returnMoney(): Map<Money, Int> {
        val currentAmount = insertedMoneyAmount
        insertedMoneyAmount = 0
        return MoneyModule.toMoneyCount(currentAmount, supportedMoneySet)
    }

    fun getItemInformations(): Set<ItemInformation> {
        return itemInformations
    }

    fun getBuyableItems(): Set<String> {
        val itemInformation = itemInformations.first()  // FIXME
        return if (insertedMoneyAmount >= itemInformation.price && itemInformation.stock > 0) {
            setOf(itemInformation.name)
        } else {
            setOf()
        }
    }

    fun buy(name: String): String? {
        return if (name in getBuyableItems()) {
            val boughtItemInformation = itemInformations.find { it.name == name }!!

            itemInformations.remove(boughtItemInformation)
            itemInformations.add(boughtItemInformation.copy(stock = boughtItemInformation.stock - 1))

            insertedMoneyAmount -= boughtItemInformation.price
            soldAmount += boughtItemInformation.price
            name
        } else {
            null
        }
    }

    fun getSoldAmount(): Int {
        return soldAmount
    }

    companion object {
        private val supportedMoneySet = setOf(Money.Ten, Money.Fifty, Money.Hundred, Money.FiveHundred, Money.Thousand)

        private val DEFAULT_ITEM_INFOS = setOf(
            ItemInformation(name = "コーラ", price = 120, stock = 5),
            ItemInformation(name = "レッドブル", price = 200, stock = 5),
            ItemInformation(name = "水", price = 100, stock = 5),
        )
    }
}
