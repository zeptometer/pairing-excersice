package vendingmachine

import org.junit.jupiter.api.Test
import vendingmachine.model.Money
import kotlin.test.assertEquals

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
}
