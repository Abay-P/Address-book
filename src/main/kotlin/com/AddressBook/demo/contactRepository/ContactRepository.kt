package com.AddressBook.demo.contactRepository

import com.AddressBook.demo.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Long>{
    fun findByLastNameContainingIgnoreCase(lastName: String): List<String>

}