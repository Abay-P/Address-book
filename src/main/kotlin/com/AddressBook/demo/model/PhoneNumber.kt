package com.AddressBook.demo.model

import com.AddressBook.demo.type.PhoneType
import jakarta.persistence.*
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy

data class PhoneNumber(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    val phoneNumber: String,
    @Enumerated(EnumType.STRING) val phoneType: PhoneType

)
