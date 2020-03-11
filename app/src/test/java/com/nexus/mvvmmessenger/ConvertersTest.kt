package com.nexus.mvvmmessenger

import com.nexus.mvvmmessenger.constant.MessageDirection
import com.nexus.mvvmmessenger.data.Converters
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersTest {

    private val dir = "OUTGOING"
    private val enum = MessageDirection.OUTGOING

    @Test
    fun messageDirectionToEnum() {
        assertEquals(enum, Converters().toDirection(dir))
    }

    @Test
    fun enumToMessageDirection() {
        assertEquals(Converters().fromDirection(enum), dir)
    }
}