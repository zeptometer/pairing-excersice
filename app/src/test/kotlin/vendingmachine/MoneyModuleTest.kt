package vendingmachine

import org.junit.jupiter.api.Test
import vendingmachine.model.Money
import kotlin.test.assertEquals
import kotlin.test.assertFails

internal class MoneyModuleTest {
    @Test
    fun `貨幣の個数のマップから、合計金額を計算できる`() {
        assertEquals(
            170, MoneyModule.calculateAmount(
                mapOf(
                    Money.Ten to 2,
                    Money.Fifty to 1,
                    Money.Hundred to 1,
                    Money.FiveHundred to 0,
                    Money.Thousand to 0
                )
            )
        )

        assertEquals(
            1270,
            MoneyModule.calculateAmount(
                mapOf(
                    Money.Ten to 2,
                    Money.Fifty to 1,
                    Money.Hundred to 2,
                    Money.FiveHundred to 0,
                    Money.Thousand to 1
                )
            )
        )
    }

    @Test
    fun `金額から貨幣の個数のマップを作る`() {
        val supportedMoneySet = setOf(Money.Ten, Money.Fifty, Money.Hundred, Money.FiveHundred, Money.Thousand)

        assertEquals(
            mapOf(
                Money.Ten to 4,
                Money.Fifty to 1,
                Money.Hundred to 1,
                Money.FiveHundred to 1,
                Money.Thousand to 2
            ),
            MoneyModule.toMoneyCount(2690, supportedMoneySet)
        )

        assertEquals(
            mapOf(
                Money.Ten to 1,
                Money.Fifty to 0,
                Money.Hundred to 2,
                Money.FiveHundred to 0,
                Money.Thousand to 3
            ),
            MoneyModule.toMoneyCount(3210, supportedMoneySet)
        )

        assertEquals(
            mapOf(
                Money.Ten to 0,
                Money.Fifty to 0,
                Money.Hundred to 0,
                Money.FiveHundred to 0,
                Money.Thousand to 5
            ),
            MoneyModule.toMoneyCount(5000, supportedMoneySet)
        )
    }

    @Test
    fun `サポートしている貨幣から貨幣の個数のマップを作れない時エラーを出す`() {
        val supportedMoneySet = setOf(Money.Ten, Money.Fifty, Money.Hundred, Money.FiveHundred, Money.Thousand)

        assertFails { MoneyModule.toMoneyCount(1, supportedMoneySet) }
    }
}
