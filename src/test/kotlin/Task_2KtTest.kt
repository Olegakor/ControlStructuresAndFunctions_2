import org.junit.Test

import org.junit.Assert.*

class Task_2KtTest {

    @Test
    fun calculateCommissionMonthlyLimit() {
        val result = calculateCommission(MASTERCARD, transferAmount = 601_000.0)
        assertEquals(0.0 ,result, 0.00001)
    }

    @Test
    fun calculateCommissionDailyLimit() {
        val result = calculateCommission(MASTERCARD, transferAmount = 151_000.0)
        assertEquals(0.0 ,result, 0.00001)
    }

    @Test
    fun calculateCommissionMastercardNoExceedLimit() {
        val result = calculateCommission(MASTERCARD, transferAmount = 75_000.0)
        assertEquals(0.0 ,result, 0.00001)
    }

    @Test
    fun calculateCommissionMastercardExceedLimit() {
        val result = calculateCommission(MASTERCARD, transferAmount = 76_000.0)
        assertEquals(26.0 ,result, 0.00001)
    }

    @Test
    fun calculateCommissionVisaMinCommission() {
        val result = calculateCommission(VISA, transferAmount = 1_000.0)
        assertEquals(35.0 ,result, 0.00001)
    }

    @Test
    fun calculateCommissionVisa() {
        val result = calculateCommission(VISA, transferAmount = 5_000.0)
        assertEquals(37.5, result, 0.00001)
    }

    @Test
    fun calculateCommissionMir() {
        val result = calculateCommission(MIR, transferAmount = 5_000.0)
        assertEquals(0.0, result, 0.00001)
    }

    @Test
    fun calculateCommissionFakeCard() {
        val result = calculateCommission("Fake", transferAmount = 5_000.0)
        assertEquals(0.0, result, 0.00001)
    }
}