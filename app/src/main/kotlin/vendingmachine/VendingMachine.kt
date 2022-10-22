package vendingmachine

class VendingMachine(private val moneyList: MutableList<Money>) {
    constructor() : this(mutableListOf())

    fun insert(money: Money) {
        moneyList.add(money)
    }

    fun getAmount(): Int {
        return moneyList.sumOf { it.value }
    }
}
