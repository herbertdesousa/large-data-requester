package org.example.models

data class Address(
    val street: String,
    val number: String,
    val complement: String,
    val neighborhood: String,
    val zip: String,
    val city: String,
    val state: String,
)