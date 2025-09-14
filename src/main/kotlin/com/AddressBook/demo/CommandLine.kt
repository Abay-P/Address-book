package com.AddressBook.demo

import com.AddressBook.demo.service.ContactService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
abstract class CommandLine(private val contactService: ContactService) : CommandLineRunner{
    override fun run(vararg args: String?) {
        println("Welcome to Address Book")
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
                "2" -> contactService.getAllContacts()
                "3" -> contactService.getContact()
                "4" -> contactService.editContact()
                "5" -> contactService.deleteContact()
                "6" -> return
                else -> println(CustomError.INVALID_OPTION)
            }
        }
    }
}