package com.zonnesoft.calculator.backend.service.impl

import com.zonnesoft.calculator.backend.service.CalculatorService
import javax.inject.Singleton

@Singleton
class CalculatorServiceImpl : CalculatorService {
    override fun sum(a: Int, b: Int): Int {
        return a + b
    }

    override fun fibonacci(n: Int): Int {
        if (n <= 1) return n
        var value = 1
        var prevValue = 1

        for (i in 2 until n) {
            val temp = value
            value += prevValue
            prevValue = temp
        }
        return value
    }
}
