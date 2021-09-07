package com.costa.matheus.domain.usecases

abstract class BaseUseCase<in T, out R> {

    abstract suspend fun call(param: T): R
}