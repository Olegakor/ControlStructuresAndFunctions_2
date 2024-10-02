const val MONTHLY_LIMIT = 600_000.0
const val DAILY_LIMIT = 150_000.0
const val MASTERCARD_LIMIT = 75_000.0

const val MASTERCARD = "Mastercard"
const val VISA = "Visa"
const val MIR = "Мир"

fun main() {
    // Примеры использования функции
    val result1 = calculateCommission(MASTERCARD, 80_000.0, 100_000.0)
    println("Комиссия: $result1")

    val result2 = calculateCommission(VISA, 0.0, 50_000.0)
    println("Комиссия: $result2")

    val result3 = calculateCommission(MIR, 100_000.0, 30_000.0)
    println("Комиссия: $result3")

    val result4 = calculateCommission(MASTERCARD, 70_000.0, 80_000.0)
    println("Комиссия: $result4")
}
fun calculateCommission(
    cardType: String,
    previousMonthTransfers: Double = 0.0,
    transferAmount: Double
): Double {
    // Проверка лимитов

    if (previousMonthTransfers + transferAmount > MONTHLY_LIMIT) {
        println("Превышен месячный лимит.")
        return 0.0
    }

    if (transferAmount > DAILY_LIMIT) {
        println("Превышен суточный лимит.")
        return 0.0
    }

    // Расчёт комиссии
    val commission: Double = when (cardType) {

        MASTERCARD -> {
            if (transferAmount <= MASTERCARD_LIMIT) {
                0.0 // Комиссия не взимается до 75 000 руб.
            } else {
                ((transferAmount-MASTERCARD_LIMIT) * 0.006) + 20 // 0,6% + 20 руб.
            }
        }

        VISA -> {
            val calculatedCommission = transferAmount * 0.0075
            if (calculatedCommission < 35) 35.0
            else calculatedCommission // Минимальная комиссия 35 руб.
        }

        MIR -> 0.0 // Комиссия не взимается

        else -> {
            println("Неизвестный тип карты.")
            0.0
        }
    }

    return commission
}