package fizzbuzz

import kotlin.test.Test
import kotlin.test.*

internal class FizzbuzzTest {
    @Test fun `stringify the given number`() {
        assertEquals(Fizzbuzz.get(1), "1")
        assertEquals(Fizzbuzz.get(2), "2")
        assertEquals(Fizzbuzz.get(28), "28")
    }

    @Test fun `return Fizz when mod 3 = 0`() {
        assertEquals(Fizzbuzz.get(3), "Fizz")
        assertEquals(Fizzbuzz.get(6), "Fizz")
    }

    @Test fun `return Buzz when mod 5 = 0`() {
        assertEquals(Fizzbuzz.get(5), "Buzz")
        assertEquals(Fizzbuzz.get(100), "Buzz")
    }

    @Test fun `return FizzBuzz when mod 15 = 0`() {
        assertEquals(Fizzbuzz.get(15), "FizzBuzz")
        assertEquals(Fizzbuzz.get(30), "FizzBuzz")
        assertEquals(Fizzbuzz.get(90), "FizzBuzz")
    }
}
