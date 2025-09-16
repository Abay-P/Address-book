package com.AddressBook.demo.service

import com.AddressBook.demo.CustomError
import com.AddressBook.demo.Interface.InputProvider
import com.AddressBook.demo.contactRepository.ContactRepository
import com.AddressBook.demo.model.Address
import com.AddressBook.demo.model.Contact
import com.AddressBook.demo.model.PhoneNumber
import com.AddressBook.demo.type.AddressType
import com.AddressBook.demo.type.PhoneType
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*
import java.time.LocalDate

@Service
class ContactService(private val contactRepository: ContactRepository, @Qualifier("consoleInputProvider") private val inputProvider: InputProvider
) {
    fun addNewContact(){
        val firstName = readNonEmptyInput("Enter first name:", inputProvider)
        if (firstName.equals("exit", ignoreCase = true)) return

        val lastName = readNonEmptyInput("Enter last name:", inputProvider)
        if (lastName.equals("exit", ignoreCase = true)) return

        var birthDate: LocalDate? = null

        while (true) {
            val input = readNonEmptyInput("Enter birth date (yyyy-MM-dd) or 'exit' to cancel:", inputProvider)
            if (input.equals("exit", ignoreCase = true)) break

            if (input.isBlank()) {
                birthDate = null
                break
            }

            try {
                birthDate = LocalDate.parse(input)
                break
            } catch (ex: Exception) {
                println("Invalid format. Please enter date as yyyy-MM-dd.")
            }
        }

        val addressCountInput = readNonEmptyInput("How many addresses do you want to add?:", inputProvider)
        if (addressCountInput.equals("exit", ignoreCase = true)) return
        val addressCount = addressCountInput.toIntOrNull() ?: 0
        val address = mutableSetOf<Address>()
        repeat(addressCount) {
            val street = readNonEmptyInput("Street:", inputProvider)
            if (street.equals("exit", ignoreCase = true)) return

            val houseNumber = readNonEmptyInput("House Number:", inputProvider)
            if (houseNumber.equals("exit", ignoreCase = true)) return

            val postalCode = readNonEmptyInput("Postal Code:", inputProvider)
            if (postalCode.equals("exit", ignoreCase = true)) return

            val city = readNonEmptyInput("City:", inputProvider)
            if (city.equals("exit", ignoreCase = true)) return

            val addressTypeInput = readNonEmptyInput("Address Type [BUSINESS/PRIVATE]:", inputProvider)
            if (addressTypeInput.equals("exit", ignoreCase = true)) return
            val addressType = AddressType.valueOf(addressTypeInput.uppercase())


            address.add(
                Address(
                    street = street,
                    houseNumber = houseNumber,
                    postalCode = postalCode,
                    city = city,
                    type = addressType
                )
            )
        }

        val phoneCountInput = readNonEmptyInput("How many phone numbers do you want to add?:", inputProvider)
        if (phoneCountInput.equals("exit", ignoreCase = true)) return
        val phoneCount = phoneCountInput.toIntOrNull() ?: 0
        val phoneNumber = mutableSetOf<PhoneNumber>()
        repeat(phoneCount) {
            val number = readNonEmptyInput("Phone Number:", inputProvider)
            if (number.equals("exit", ignoreCase = true)) return

            val phoneTypeInput = readNonEmptyInput(
                "Phone Type [LANDLINE_BUSINESS, LANDLINE_PRIVATE, MOBILE_BUSINESS, MOBILE_PRIVATE]:",
                inputProvider
            )
            if (phoneTypeInput.equals("exit", ignoreCase = true)) return
            val phoneType = PhoneType.valueOf(phoneTypeInput.uppercase())

            phoneNumber.add(
                PhoneNumber(
                    phoneNumber = number,
                    phoneType = phoneType
                )
            )
        }

        val contact = Contact(
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            address = address,
            phoneNumber = phoneNumber
        )

        address.forEach { it.contact = contact }
        phoneNumber.forEach { it.contact = contact }

        contact.address.addAll(address)
        contact.phoneNumber.addAll(phoneNumber)

        contactRepository.save(contact)
        println("Contact saved successfully")
    }
    fun editContact(){
        println("Enter Id to edit:")
        val idInput = readNonEmptyInput("Enter ID", inputProvider)
        if (idInput.equals("exit", ignoreCase = true)) return
        val id = validatingNumber(idInput)

        if (id == null) {
            println(CustomError.INVALID_ID)
            return
        }
        val existingContact: Contact? = contactRepository.findById(id).orElse(null)
        if(existingContact != null){
            addNewContact()
        }
        
    }
    fun deleteContact(){
        val idInput = readNonEmptyInput("Enter ID to delete:", inputProvider)
        if (idInput.equals("exit", ignoreCase = true)) return
        val id = validatingNumber(idInput)
        if (id == null) {
            println(CustomError.INVALID_ID)
            return
        }
        if(contactRepository.findAllList().none { el -> el.id == id }){
            println("Contact not Found")
        }else{
            contactRepository.deleteById(id)
            println("Contact deleted successfully")
        }


    }
    fun getAllContacts(): List<Contact>{
       return contactRepository.findAllList()
    }
    fun getContact(): Contact? {
        val idInput = readNonEmptyInput("Enter ID to get a contact:", inputProvider)
        if (idInput.equals("exit", ignoreCase = true)) return null
        val id = validatingNumber(idInput)
        if (id == null) {
            println(CustomError.INVALID_ID)
            return null
        }
        return contactRepository.findById(id).orElse(null)
    }
    fun readNonEmptyInput(prompt: String, inputProvider: InputProvider): String {
        while (true) {
            try {
                println(prompt)
                val input = inputProvider.readln().trim()
                if (input.isNotEmpty()) return input
                else println("Input cannot be empty. Please try again.")
            } catch (ex: Exception) {
                println("Invalid input. Please try again.")
            }
        }
    }

    fun validatingNumber(idInput: String): Long? {
        var value: Long?

        while (true) {
            if (idInput.isBlank()) {
                value = null
                break
            }

            try {
                value = idInput.toLongOrNull()
                break
            } catch (ex: Exception) {
                println("Invalid format")
            }
        }
        return value
    }
}