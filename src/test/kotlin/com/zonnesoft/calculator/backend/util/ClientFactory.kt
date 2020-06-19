package com.zonnesoft.calculator.backend.util

import com.zonnesoft.calculator.backend.CalculatorServiceGrpc
import com.zonnesoft.calculator.backend.ReactorCalculatorServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel

@Factory
class ClientFactory {
    @Bean
    fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel) =
        CalculatorServiceGrpc.newBlockingStub(channel)

    @Bean
    fun rxStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel) =
        ReactorCalculatorServiceGrpc.newReactorStub(channel)
}

