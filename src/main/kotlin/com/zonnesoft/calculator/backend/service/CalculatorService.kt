package com.zonnesoft.calculator.backend.service

interface CalculatorService  {
    fun sum(a: Int, b: Int): Int
    fun fibonacci(n: Int): Int
}
