package org.example.models

/*
paper
  - type (budget, receipt)
  - add_bank_details
  - add_signatures
  - customer
  - block
    - title
    - description
    - price
    - materials
    - places
      - room
      - floor
      - devices
        - type
        - brand
        - capacity
        - mode
        - quantity
*/

data class Paper(
    var type: Type,
    var addBankDetails: Boolean,
    var addSignatures: Boolean,
    var customer: Customer,
    var blocks: List<Block>
) {
    enum class Type {
        Budget,
        Receipt
    }

    data class Block(
        val title: String,
        val description: String,
        val price: Int,
        val materials: List<String>,
        val places: List<Place>,
    ) {
        data class Place(
            val room: String,
            val floor: String,
            val devices: List<Device>
        ) {
            data class Device(
                val type: String,
                val brand: String,
                val capacity: String,
                val mode: String,
                val quantity: Int,
            )
        }
    }
}