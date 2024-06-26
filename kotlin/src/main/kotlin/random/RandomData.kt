package random

import org.example.models.Address
import java.io.File
import kotlin.random.Random

object RandomData {
    private val namesTxt = File("companies-first-name.txt").readText().split("\n")
    private val addressesTxt = File("addresses.csv").readText().split("\n")

    fun address(): Address {
        val picked = addressesTxt.random().split(",")

        return Address(
            street = picked[0],
            number = picked[1],
            complement = picked[2],
            neighborhood = picked[5],
            zip = picked[6],
            city = picked[3],
            state = picked[4],
        )
    }

    fun name(): String = namesTxt.random()

    fun phone(): String {
        val areaCode = (10..23).random()

        val firstPart = (10000..99999).random()

        val secondPart = (1000..9999).random()

        val phoneNumber = "($areaCode) $firstPart-$secondPart"

        return phoneNumber
    }

    fun legalEntityDocument(): String {
        fun check(doc: IntArray): Int {
            val weights = if (doc.size == 12) intArrayOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2) else intArrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
            val sum = doc.indices.sumBy { doc[it] * weights[it] }
            val remainder = sum % 11
            return if (remainder < 2) 0 else 11 - remainder
        }

        fun format(d: IntArray): String {
            return "${d[0]}${d[1]}.${d[2]}${d[3]}${d[4]}.${d[5]}${d[6]}${d[7]}/${d[8]}${d[9]}${d[10]}${d[11]}-${d[12]}${d[13]}"
        }

        val cnpj = IntArray(12) { Random.nextInt(0, 10) }
        val cnpjWithFirstCheckDigit = cnpj + check(cnpj)
        val cnpjWithBothCheckDigits = cnpjWithFirstCheckDigit + check(cnpjWithFirstCheckDigit)
        return format(cnpjWithBothCheckDigits)
    }

    fun naturalPersonDocument(): String {
        fun check(doc: IntArray, weight: Int): Int {
            val sum = doc.indices.sumBy { doc[it] * (weight - it) }
            val remainder = sum % 11
            return if (remainder < 2) 0 else 11 - remainder
        }

        fun format(d: IntArray): String {
            return "${d[0]}${d[1]}${d[2]}.${d[3]}${d[4]}${d[5]}.${d[6]}${d[7]}${d[8]}-${d[9]}${d[10]}"
        }

        val cpf = IntArray(9) { Random.nextInt(0, 10) }
        val cpfWithFirstCheckDigit = cpf + check(cpf, 10)
        val cpfWithBothCheckDigits = cpfWithFirstCheckDigit + check(cpfWithFirstCheckDigit, 11)
        return format(cpfWithBothCheckDigits)
    }
}