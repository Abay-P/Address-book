package com.AddressBook.demo
import com.AddressBook.demo.Interface.InputProvider
import org.springframework.stereotype.Component

@Component
class ConsoleInputProvider : InputProvider {
    override fun readln(): String = kotlin.io.readln() ?: ""
    override fun readlnOrNull(): String? = kotlin.io.readlnOrNull()
}