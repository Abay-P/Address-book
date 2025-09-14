package com.AddressBook.demo.model

import jakarta.persistence.CascadeType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDate

data class Contact (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    var firstName: String,
    var lastName: String,
    var birthDate: LocalDate? = null,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true) val address: MutableList<Address> = mutableListOf(),
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true) val phoneNumber: MutableList<PhoneNumber> = mutableListOf()
)


