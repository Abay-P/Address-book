package com.AddressBook.demo.model

import jakarta.persistence.*
import java.time.LocalDate
@Entity
 class Contact (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    var firstName: String,
    var lastName: String,
    var birthDate: LocalDate? = null,
    @OneToMany(mappedBy = "contact", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER) val address: MutableSet<Address> = mutableSetOf(),
    @OneToMany(mappedBy= "contact", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER) val phoneNumber: MutableSet<PhoneNumber> = mutableSetOf()
){
    override fun equals(other: Any?): Boolean =
        (this === other) || (other is Contact && id != 0L && id == other.id)

    override fun hashCode(): Int = id.hashCode()
}


