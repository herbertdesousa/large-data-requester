package org.example.airproject.random

import java.io.File
import kotlin.random.Random

object Utils {
    val companyFirstNames = File("companies-first-name.txt").readText().split("\n")
    val companyLastNames =  File("companies-last-name.txt").readText().split("\n")
    val addresses = File("addresses.csv").readText().split("\n")

    fun randomCompanyFullName(): String {
        return "${companyFirstNames.random()} ${companyLastNames.random()}"
    }
    fun generateLegalEntityDocument(): String {
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

    fun generateNaturalPersonDocument(): String {
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

    fun generatePrice(from: Int, to: Int): Int {
        val range = (from / 10)..(to / 10)
        val randomNumber = Random.nextInt(range.first, range.last + 1)
        return randomNumber * 10
    }

    fun <T> randomItems(array: Array<T>, quantity: Int): List<T> {
        require(quantity <= array.size) { "Quantity should be less than or equal to array size" }

        val shuffledArray = array.toList().shuffled()

        return shuffledArray.subList(0, quantity)
    }

    val paperBlockTitle = arrayOf("Infraestrutura", "Instalação", "Serviço", "Manutenção Mensal")
    val paperBlockDescription =  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    val paperBlockMaterials = arrayOf(
        "Cano de cobre",
        "Isolamento térmico",
        "Caixa de infra",
        "Cabo pp(4 vias)",
        "Cano(pvc)",
        "Conexões(pvc)",
        "Silver tape",
        "Buchas",
        "Abraçadeiras",
        "Parafusos",
        "Pés de borracha",
        "Carga de gás",
        "Bomba de vácuo",
        "Nitrogênio",
        "Suporte de condensadora",
        "Cano de PVC",
    )

    val paperBlockPlaceNames = arrayOf(
        "Sala",
        "Sacada",
        "Suite I",
        "Suite II",
        "Suite III",
        "Quarto de Hóspede",
        "Suite Master",
        "Quarto I",
        "Quarto II",
        "Quarto de Visita",
        "Suite IV",
    )
    val paperBlockPlaceRooms = arrayOf(
        "",
        "",
        "",
        "",
        "",
        "",
        "Pavimento I",
        "Pavimento II",
        "Térreo",
        "Primeiro Andar",
        "Segundo Andar",
        "Terceiro Andar",
        "Segundo Piso",
        "Terceiro Piso",
    )

    val paperBlockPlaceDeviceTypes = arrayOf(
        "Evaporadora",
        "Condensadora",
        "Ar Condicionado",
    )
    val paperBlockPlaceDeviceModes = arrayOf(
        "Split Cassete(K7) 1 via",
        "Split Cassete(K7) 4 vias",
        "Split",
        "Split Hi-Wall",
        "Split Piso-Teto",
        "Split Quatro-Lados",
        "Split Canto-Teto",
        "Split Inverter",
        "Split Janela",
        "Portátil",
        "VRF",
    )
    val paperBlockPlaceDeviceBrands = arrayOf(
        "Samsung",
        "Fujitsu",
        "LG",
        "Midea",
        "Carrier",
        "Elgin",
        "Gree",
        "Hitachi",
        "Springer",
        "Daikin",
        "Consul",
        "Komeco",
        "Fontaine",
    )
    val paperBlockPlaceDeviceCapacities = arrayOf(
        "7.000 BTUS",
        "9.000 BTUS",
        "12.000 BTUS",
        "15.000 BTUS",
        "16.000 BTUS",
        "18.000 BTUS",
        "21.000 BTUS",
        "24.000 BTUS",
        "27.000 BTUS",
        "28.000 BTUS",
        "30.000 BTUS",
        "32.000 BTUS",
        "36.000 BTUS",
        "40.000 BTUS",
        "42.000 BTUS",
        "48.000 BTUS",
        "56.000 BTUS",
    )
}