package me.tap.domain.common.usecase

abstract class UseCase<in Param, out Output> {

    abstract operator fun invoke(param: Param): Output
}

operator fun <Output> UseCase<Unit, Output>.invoke() = this(Unit)