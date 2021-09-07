package com.costa.matheus.burger.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel(), CoroutineScope {

    override val coroutineContext = MainScope().coroutineContext

    protected val jobs = ArrayList<Job>()

    infix fun ArrayList<Job>.add(job: Job) {this.add(job)}

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { if (!it.isCancelled) it.cancel() }
    }
}