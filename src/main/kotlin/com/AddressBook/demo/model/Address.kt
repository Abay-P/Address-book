package com.AddressBook.demo.model

import com.AddressBook.demo.type.AddressType
import jakarta.persistence.*

data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var street: String,
    var houseNumber: String,
    var postalCode: String?,
    var city: String?,

    @Enumerated(EnumType.STRING)
    var type: AddressType

)
