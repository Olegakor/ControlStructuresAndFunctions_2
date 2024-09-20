fun main() {
    // Примеры использования функции
    val result1 = calculateCommission("Mastercard", 80_000.0, 100_000.0)
    println("Комиссия: ${result1.first}, Сообщение: ${result1.second}")

    val result2 = calculateCommission("Visa", 0.0, 50_000.0)
    println("Комиссия: ${result2.first}, Сообщение: ${result2.second}")

    val result3 = calculateCommission("Мир", 100_000.0, 30_000.0)
    println("Комиссия: ${result3.first}, Сообщение: ${result3.second}")

    val result4 = calculateCommission("Mastercard", 70_000.0, 80_000.0)
    println("Комиссия: ${result4.first}, Сообщение: ${result4.second}")
}
fun calculateCommission(
    cardType: String = "Мир",
    previousMonthTransfers: Double = 0.0,
    transferAmount: Double
): Pair<Double, String> {
    // Лимиты
    val monthlyLimit = 600_000.0
    val dailyLimit = 150_000.0
    val mastercardLimit = 75_000.0

    // Проверка лимитов
    if (transferAmount > dailyLimit) {
        return Pair(0.0, "Превышен суточный лимит.")
    }

    if (previousMonthTransfers + transferAmount > monthlyLimit) {
        return Pair(0.0, "Превышен месячный лимит.")
    }

    // Расчёт комиссии
    val commission: Double = when (cardType) {
        "Mastercard" -> {
            if (previousMonthTransfers <= mastercardLimit) {
                0.0 // Комиссия не взимается до 75 000 руб.
            } else {
                (transferAmount * 0.006) + 20 // 0,6% + 20 руб.
            }
        }
        "Visa" -> {
            val calculatedCommission = transferAmount * 0.0075
            if (calculatedCommission < 35) 35.0 else calculatedCommission // Минимальная комиссия 35 руб.
        }
        "Мир" -> {
            0.0 // Комиссия не взимается
        }
        else -> {
            throw IllegalArgumentException("Неизвестный тип карты.")
        }
    }

    return Pair(commission, "Комиссия успешно рассчитана.")
}