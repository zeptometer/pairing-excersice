package vendingmachine

import vendingmachine.model.Money

object MoneyModule {
    fun calculateAmount(moneyMap: Map<Money, Int>): Int {
        return moneyMap.entries.sumOf { (money, num) -> money.value * num }
    }
}
