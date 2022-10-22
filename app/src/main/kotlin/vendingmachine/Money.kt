package vendingmachine

enum class Money(val value: Int) {
    One(1),
    Five(5),
    Ten(10),
    Fifty(50),
    Hundred(100),
    FiveHundred(500),
    Thousand(1000),
    FiveThousand(5000),
    TenThousand(10000),
}
