package org.example.models

class Customer(
    val name: String,
    val type: Type,
    val document: String,
    val representatives: List<Representative>,
    val addresses: List<Address>,
) {
    data class Representative(
        val name: String,
        val type: Type,
    ) {
        enum class Type {
            Engineer,
            Architect
        }
    }

    enum class Type {
        LegalEntity,
        NaturalPerson
    }
}