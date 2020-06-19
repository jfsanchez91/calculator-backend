package com.zonnesoft.calculator.backend

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.zonnesoft.calculator.backend")
		.start()
}

