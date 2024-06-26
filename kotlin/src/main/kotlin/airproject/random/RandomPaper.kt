package org.example.airproject.random

import org.example.models.Customer
import org.example.models.Paper
import kotlin.random.Random

class RandomPaper(
    customer: Customer = RandomCustomer().customer,
) {
    private val paper: Paper = Paper(
        type = Paper.Type.entries.toTypedArray().random(),
        addSignatures = Random.nextBoolean(),
        addBankDetails = Random.nextBoolean(),
        customer = customer,
        blocks = List(Random.nextInt(1, 5)) {
            Paper.Block(
                title = Utils.paperBlockTitle.random(),
                description = Utils.paperBlockDescription.run { substring(0, indices.random()) },
                price = Utils.generatePrice(100, 5000),
                materials = Utils.randomItems(
                    Utils.paperBlockMaterials,
                    Random.nextInt(1, Utils.paperBlockMaterials.size)
                ),
                places = List(Random.nextInt(1, 10)) {
                    Paper.Block.Place(
                        room = Utils.paperBlockPlaceNames.random(),
                        floor = Utils.paperBlockPlaceRooms.random(),
                        devices = List(Random.nextInt(1, 10)) {
                            Paper.Block.Place.Device(
                                type = Utils.paperBlockPlaceDeviceTypes.random(),
                                brand = Utils.paperBlockPlaceDeviceBrands.random(),
                                capacity = Utils.paperBlockPlaceDeviceCapacities.random(),
                                mode = Utils.paperBlockPlaceDeviceModes.random(),
                                quantity = Random.nextInt(1, 3),
                            )
                        }
                    )
                }
            )
        }
    )

    fun print() {
        println("type: ${paper.type}")
        println("addBankDetails: ${paper.addBankDetails}")
        println("addSignatures: ${paper.addSignatures}")
        println("customer:")
        println("  name: ${paper.customer.name}")
        println("  type: ${paper.customer.type}")
        println("  document: ${paper.customer.document}")
        println("  representatives: ${paper.customer.representatives}")
        println("  addresses(${paper.customer.addresses.size})")
        paper.customer.addresses.forEach {
            println("    - ${it.street} ${it.number} ${it.complement} - ${it.neighborhood} - ${it.city}, ${it.state}")
        }
        println("blocks(${paper.blocks.size}):")
        paper.blocks.forEach {
            println("  ${it.title}")
            println("    description: ${it.description}")
            println("    price: ${it.price}")
            println("    materials: ${it.materials.joinToString(", ")}")
            println("    places(${it.places.size}):")
            it.places.forEach {
                println("      (${it.floor}) ${it.room}")
                println("      devices(${it.devices.size}):")
                it.devices.forEach {
                    println("        - ${it.quantity}x ${it.type} ${it.brand} ${it.mode} ${it.capacity}")
                }
            }
        }
    }
}

