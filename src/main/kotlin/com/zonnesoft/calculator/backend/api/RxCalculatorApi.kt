package com.zonnesoft.calculator.backend.api

import com.zonnesoft.calculator.backend.FibonacciReply
import com.zonnesoft.calculator.backend.FibonacciRequest
import com.zonnesoft.calculator.backend.ReactorCalculatorServiceGrpc
import com.zonnesoft.calculator.backend.SumReply
import com.zonnesoft.calculator.backend.SumRequest
import com.zonnesoft.calculator.backend.service.CalculatorService
import io.grpc.Status
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.inject.Singleton

@Singleton
class RxCalculatorApi(
    private val service: CalculatorService
) : ReactorCalculatorServiceGrpc.CalculatorServiceImplBase() {

    override fun sum(request: Mono<SumRequest>): Mono<SumReply> =
        request.map { r -> SumReply.newBuilder().setResult(service.sum(r.a, r.b)).build() }

    override fun fibonacci(request: Mono<FibonacciRequest>): Flux<FibonacciReply> =
        Flux.from(request).flatMap { r ->
            if (r.min > r.max) Flux.error(
                Status.INVALID_ARGUMENT
                    .withDescription("'max' argument must be bigger than 'min'.")
                    .asRuntimeException()
            )
            else Flux.fromIterable(r.min..r.max).map {
                FibonacciReply.newBuilder().setX(it).setFibX(service.fibonacci(it)).build()
            }
        }
}
