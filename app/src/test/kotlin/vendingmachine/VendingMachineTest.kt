package vendingmachine

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import vendingmachine.model.ItemInformation
import vendingmachine.model.Money
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class VendingMachineTest {
    companion object {
        @JvmStatic
        fun arguments(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Money.Ten, 10),
                Arguments.of(Money.Fifty, 50),
                Arguments.of(Money.Hundred, 100),
                Arguments.of(Money.FiveHundred, 500),
                Arguments.of(Money.Thousand, 1000)
            )

        @JvmStatic
        fun supportedMoney(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Money.Ten),
                Arguments.of(Money.Fifty),
                Arguments.of(Money.Hundred),
                Arguments.of(Money.FiveHundred),
                Arguments.of(Money.Thousand)
            )

        @JvmStatic
        fun unsupportedMoney(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Money.One),
                Arguments.of(Money.Five),
                Arguments.of(Money.FiveThousand),
                Arguments.of(Money.TenThousand),
            )
    }

    @Test
    fun `投入金額の初期値は0円である`() {
        val vm = VendingMachine()
        assertEquals(0, vm.getAmount())
    }

    @ParameterizedTest
    @MethodSource("arguments")
    fun `{money}円玉を投入でき、投入金額が{money}円になる`(money: Money, expectedAmount: Int) {
        val vm = VendingMachine()
        vm.insert(money)
        assertEquals(expectedAmount, vm.getAmount())
    }

    @Test
    fun `複数回投入できる`() {
        val vm = VendingMachine()
        vm.insert(Money.Ten)
        vm.insert(Money.Fifty)
        vm.insert(Money.Ten)
        vm.insert(Money.Thousand)
        assertEquals(1070, vm.getAmount())
    }

    @Test
    fun `払い戻すと投入金額が0円になる`() {
        val vm = VendingMachine()
        vm.insert(Money.Ten)
        vm.returnMoney()
        assertEquals(0, vm.getAmount())
    }

    @Test
    fun `払い戻すと投入金額分の金が戻ってくる`() {
        val vm = VendingMachine()
        vm.insert(Money.Ten)
        vm.insert(Money.Ten)
        vm.insert(Money.Hundred)
        vm.insert(Money.Fifty)
        assertEquals(
            mapOf(
                Money.Ten to 2,
                Money.Fifty to 1,
                Money.Hundred to 1,
                Money.FiveHundred to 0,
                Money.Thousand to 0
            ), vm.returnMoney()
        )
    }

    @Test
    fun `払い戻すと投入金額分の金が戻ってくる 2(同じ貨幣とは限らない)`() {
        val vm = VendingMachine()
        vm.insert(Money.Ten)
        vm.insert(Money.Thousand)
        vm.insert(Money.Hundred)
        vm.insert(Money.Hundred)
        vm.insert(Money.Ten)
        vm.insert(Money.Fifty)
        vm.insert(Money.Fifty)
        assertEquals(
            mapOf(
                Money.Ten to 2,
                Money.Fifty to 0,
                Money.Hundred to 3,
                Money.FiveHundred to 0,
                Money.Thousand to 1
            ), vm.returnMoney()
        )
    }

    @ParameterizedTest
    @MethodSource("unsupportedMoney")
    fun `1,5円玉,5000,10000円札を投入すると、それがそのまま返ってくる`(money: Money) {
        val vm = VendingMachine()
        assertEquals(money, vm.insert(money))
        assertEquals(0, vm.getAmount())
    }

    @ParameterizedTest
    @MethodSource("supportedMoney")
    fun `それ以外の貨幣の場合、投入時に何も帰ってこない`(money: Money) {
        val vm = VendingMachine()
        assertEquals(null, vm.insert(money))
    }

    @Test
    fun `在庫情報を取得する`() {
        val vm = VendingMachine()
        assertEquals(
            ItemInformation(name = "コーラ", price = 120, stock = 5),
            vm.getItemInformation()
        )
    }
}
