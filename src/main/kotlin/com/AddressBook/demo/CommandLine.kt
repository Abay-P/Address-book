package com.AddressBook.demo

import com.AddressBook.demo.model.Contact
import com.AddressBook.demo.service.ContactService
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CommandLine(private val contactService: ContactService) : CommandLineRunner{
    override fun run(vararg args: String?) {
        println("Welcome to Address Book")
        while (true) {
            println("Choose action: " +
                    "[1] Add Contact " +
                    "[2] List Contact " +
                    "[3] View Particular Contact " +
                    "[4] Edit Contact " +
                    "[5] Delete Contact " +
                    "[6] Exit")
            when (readlnOrNull()?.trim()) {
                "1" -> contactService.addNewContact()
                "2" -> {
                    val contacts = contactService.getAllContacts()
                    if (contacts.isEmpty()) {
                        println("No contacts found")
                    } else {
                        printContactsAsJson(contacts)
                    }
                }
                "3" -> {
                    val contacts = contactService.getContact()
                    if(contacts == null){
                        println("No contacts found")
                    }else{
                        printContactsAsJson(listOf(contacts) )
                    }

                }
                "4" -> contactService.editContact()
                "5" -> contactService.deleteContact()
                "6" -> return
                else -> println(CustomError.INVALID_OPTION)
            }
        }
    }

    fun printContactsAsJson(contacts: List<Contact>) {
        val mapper = jacksonObjectMapper()
            .registerKotlinModule()
            .registerModule(JavaTimeModule())

        val json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contacts)
        println(json)
    }
}