package com.AddressBook.demo.contactRepository

import com.AddressBook.demo.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Long>{
    fun findByLastNameContainingIgnoreCase(lastName: String): List<String>

    @Query("SELECT DISTINCT c FROM Contact c LEFT JOIN FETCH c.address LEFT JOIN FETCH c.phoneNumber")
    fun findAllList(): List<Contact>
}