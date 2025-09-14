package com.AddressBook.demo

import com.AddressBook.demo.Interface.InputProvider
import com.AddressBook.demo.contactRepository.ContactRepository
import com.AddressBook.demo.model.Contact
import com.AddressBook.demo.service.ContactService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

import org.mockito.kotlin.whenever
import org.mockito.kotlin.mock

@SpringBootTest
class DemoApplicationTests {
	private lateinit var contactRepository: ContactRepository
	private lateinit var inputProvider: InputProvider
	private lateinit var contactService: ContactService

	@BeforeEach
	fun setUp() {
		contactRepository = mock()
		inputProvider = mock()
		contactService = ContactService(contactRepository, inputProvider)
	}

	@Test
	fun `getContact returns expected contact`() {
		val expectedContact = Contact(
			firstName = "John",
			lastName = "Doe",
			birthDate = null,
			address = mutableListOf(),
			phoneNumber = mutableListOf()
		)

		whenever(inputProvider.readln()).thenReturn("1")
		whenever(contactRepository.findById(1L)).thenReturn(Optional.of(expectedContact))

		val result = contactService.getContact()

		assertEquals("John", result?.firstName)
	}
}
