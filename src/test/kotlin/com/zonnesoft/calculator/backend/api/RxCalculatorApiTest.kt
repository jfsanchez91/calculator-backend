package com.zonnesoft.calculator.backend.api

import com.zonnesoft.calculator.backend.CalculatorServiceGrpc
import com.zonnesoft.calculator.backend.FibonacciRequest
import com.zonnesoft.calculator.backend.ReactorCalculatorServiceGrpc
import com.zonnesoft.calculator.backend.SumRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import javax.inject.Inject
import kotlin.random.Random

@MicronautTest
internal class RxCalculatorApiTest {

    @Inject
    lateinit var blockingStub: CalculatorServiceGrpc.CalculatorServiceBlockingStub

    @Inject
    lateinit var rxStub: ReactorCalculatorServiceGrpc.ReactorCalculatorServiceStub

    @Test
    fun `sum must work`() {
        val a = Random(10).nextInt()
        val b = Random(10).nextInt()
        val request = SumRequest.newBuilder().setA(a).setB(b).build()

        StepVerifier.create(rxStub.sum(request))
            .expectNextMatches { it.result == a + b }
            .verifyComplete()
    }

    @Test
    fun `fibonacci must work`() {
        val min = 0
        val fibMin = 0
        val max = 500
        val fibMax = 315178285
        val request = FibonacciRequest.newBuilder().setMin(min).setMax(max).build()

        StepVerifier.create(rxStub.fibonacci(request))
            .expectNextMatches {
                it.x == min &&
                it.fibX == fibMin
            }
            .expectNextCount(499)
            .expectNextMatches {
                it.x == max &&
                it.fibX == fibMax
            }
            .verifyComplete()
    }

    @Test
    fun `fibonacci must fail`() {
        val min = 11
        val max = 10
        val request = FibonacciRequest.newBuilder().setMin(min).setMax(max).build()

        StepVerifier.create(rxStub.fibonacci(request))
            .expectErrorMatches {
                if (it is StatusRuntimeException) {
                    it.status.code == Status.INVALID_ARGUMENT.code
                } else false
            }
            .verify()
    }
}
