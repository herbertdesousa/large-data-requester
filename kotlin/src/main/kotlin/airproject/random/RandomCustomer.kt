package org.example.airproject.random

import org.example.models.Address
import org.example.models.Customer
import kotlin.random.Random

class RandomCustomer {
    private val randomType = Customer.Type.entries.toTypedArray().random()

    val customer: Customer = Customer(
        name = Utils.randomCompanyFullName(),
        type = randomType,
        document = if (randomType === Customer.Type.LegalEntity) Utils.generateLegalEntityDocument() else Utils.generateNaturalPersonDocument(),
        representatives = List(Random.nextInt(0, 5)) {
            Customer.Representative(
                Utils.randomCompanyFullName(),
                Customer.Representative.Type.entries.toTypedArray().random()
            )
        },
        addresses = List(Random.nextInt(1, 5)) {
            val picked = Utils.addresses.random().split(",")

            Address(
                picked[0],
                picked[1],
                picked[2],
                picked[3],
                picked[4],
                picked[5],
                picked[6],
            )
        },
    )
}