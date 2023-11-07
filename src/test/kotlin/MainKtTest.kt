import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calcCommission_MasterCard() {

        //тест на сумму полностью превышающую месячный лимит
        var result = calcCommission(
            1000.0,
            cardType = "MasterCard",
            initSum = 75_000.0
        )
        assertEquals(26.0, result, 0.1)

        //тест на сумму частично превышающую месячный лимит
        result = calcCommission(
            1000.0,
            cardType = "MasterCard",
            initSum = 74_950.0
        )
        assertEquals(25.7, result, 0.1)

        //тест на сумму в пределах месячного лимита
        result = calcCommission(
            1000.0,
            cardType = "MasterCard",
            initSum = 500.0
        )
        assertEquals(0.0, result, 0.1)

    }

    @Test
    fun calcCommission_VKPay() {

        //тест на нулевую комиссию
        val result = calcCommission(
            14999.0,
            initSum = 0.0
        )
        assertEquals(0.0, result, 0.1)

    }

    @Test
    fun calcCommission_VisaMir() {

        //тест на минимальную комиссию
        var result = calcCommission(
            100.0,
            cardType = "Visa",
            initSum = 0.0
        )
        assertEquals(35.0, result, 0.1)

        //тест на сумму, превышающую минимальную комиссию
        result = calcCommission(
            10000.0,
            cardType = "Мир",
            initSum = 0.0
        )
        assertEquals(75.0, result, 0.1)

        //тест на сумму, превышающую допустимый разовый лимит
        result = calcCommission(
            200_000.0,
            cardType = "Мир",
            initSum = 0.0
        )
        assertEquals(-1.0, result, 0.1)
    }

}