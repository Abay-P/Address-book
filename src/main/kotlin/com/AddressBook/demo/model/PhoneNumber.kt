package com.AddressBook.demo.model

import com.AddressBook.demo.type.PhoneType
import jakarta.persistence.*
@Entity
data class PhoneNumber(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    val phoneNumber: String,
    @Enumerated(EnumType.STRING) val phoneType: PhoneType,
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    var contact: Contact? = null
)
