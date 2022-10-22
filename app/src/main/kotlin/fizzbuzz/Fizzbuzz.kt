package fizzbuzz

object Fizzbuzz {
    fun get(n: Int): String {
        return if (n % 15 == 0) {
            "FizzBuzz"
        } else if (n % 3 == 0) {
            "Fizz"
        } else if (n % 5 == 0) {
            "Buzz"
        } else {
            n.toString()
        }
    }
}
