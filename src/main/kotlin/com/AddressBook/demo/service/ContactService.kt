package com.AddressBook.demo.service

import com.AddressBook.demo.CustomError
import com.AddressBook.demo.Interface.InputProvider
import com.AddressBook.demo.contactRepository.ContactRepository
import com.AddressBook.demo.model.Address
import com.AddressBook.demo.model.Contact
import com.AddressBook.demo.model.PhoneNumber
import com.AddressBook.demo.type.AddressType
import com.AddressBook.demo.type.PhoneType
import org.springframework.stereotype.Service
import java.util.*
import java.time.LocalDate

@Service
class ContactService(private val contactRepository: ContactRepository,  private val inputProvider: InputProvider) {
    fun addNewContact(){
        println("Enter first name:")
        val firstName = inputProvider.readln()
        if (firstName.equals("exit", ignoreCase = true)) return

        println("Enter last name:")
        val lastName = inputProvider.readln()
        if (lastName.equals("exit", ignoreCase = true)) return

        println("Enter birth date (yyyy-MM-dd):")
        val birthDateInput = inputProvider.readlnOrNull()
        if (birthDateInput.equals("exit", ignoreCase = true)) return

        val birthDate = if (birthDateInput.isNullOrBlank()) null else LocalDate.parse(birthDateInput)

        val address = mutableListOf<Address>()
        println("How many addresses do you want to add?:")
        val addressCountInput = inputProvider.readlnOrNull()
        if (addressCountInput.equals("exit", ignoreCase = true)) return

        val addressCount = addressCountInput?.toIntOrNull() ?: 0
        repeat(addressCount) {
            println("Street:")
            val street = inputProvider.readln()
            if (street.equals("exit", ignoreCase = true)) return

            println("House Number:")
            val houseNumber = inputProvider.readln()
            if (houseNumber.equals("exit", ignoreCase = true)) return

            println("Postal Code:")
            val postalCode = inputProvider.readlnOrNull()
            if (postalCode.equals("exit", ignoreCase = true)) return

            println("City:")
            val city = inputProvider.readlnOrNull()
            if (city.equals("exit", ignoreCase = true)) return

            println("Address Type [BUSINESS/PRIVATE]:")
            val typeInput = inputProvider.readln()
            if (typeInput.equals("exit", ignoreCase = true)) return
            val type = AddressType.valueOf(typeInput.uppercase())

            address.add(Address(
                street = street,
                houseNumber = houseNumber,
                postalCode = postalCode,
                city = city,
                type = type
            ))
        }

        val phoneNumbers = mutableListOf<PhoneNumber>()
        println("How many phone numbers do you want to add? :")
        val phoneCountInput = inputProvider.readlnOrNull()
        if (phoneCountInput.equals("exit", ignoreCase = true)) return

        val phoneCount = phoneCountInput?.toIntOrNull() ?: 0
        repeat(phoneCount) {
            println("Phone Number:")
            val number = inputProvider.readln()
            if (number.equals("exit", ignoreCase = true)) return

            println("Phone Type [LANDLINE_BUSINESS, LANDLINE_PRIVATE, MOBILE_BUSINESS, MOBILE_PRIVATE]:")
            val typeInput = inputProvider.readln()
            if (typeInput.equals("exit", ignoreCase = true)) return
            val type = PhoneType.valueOf(typeInput.uppercase())

            phoneNumbers.add(PhoneNumber(phoneNumber = number, phoneType = type))
        }

        val contact = Contact(
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            address = address,
            phoneNumber = phoneNumbers
        )
        contactRepository.save(contact)
        
    }
    fun editContact(){
        println("Enter Id to edit:")
        val idInput = readlnOrNull()
        val id = idInput?.toLongOrNull()

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
        val idInput = inputProvider.readlnOrNull()
        val id = idInput?.toLongOrNull()
        if (id == null) {
            println(CustomError.INVALID_ID)
            return
        }
        contactRepository.deleteById(id)
    }
    fun getAllContacts(): List<Contact>{
       return contactRepository.findAll()
    }
    fun getContact(): Contact? {
        val idInput = inputProvider.readlnOrNull()
        val id = idInput?.toLongOrNull()
        if (id == null) {
            println(CustomError.INVALID_ID)
            return null
        }
        return contactRepository.findById(id).orElse(null)
    }
}