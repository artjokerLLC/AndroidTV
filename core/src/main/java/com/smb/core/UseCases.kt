package com.smb.core

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver

abstract class BaseUseCase {

    abstract val schedulers: Map<SchedulerType, Scheduler>
    protected var disposable: Disposable = Disposables.empty()

    fun cancel() {
        disposable.dispose()
    }

    enum class SchedulerType {
        WORK,
        WORK_RESULT
    }
}

sealed class UseCase<in P, R> : BaseUseCase() {

    protected abstract fun buildObservable(params: P): Observable<R>

    protected fun doExecute(params: P, o: DisposableObserver<R>) {
        cancel()
        disposable = o
        buildObservable(params)
                .subscribeOn(schedulers[SchedulerType.WORK])
                .observeOn(schedulers[SchedulerType.WORK_RESULT])
                .subscribe(o)
    }

    abstract class ParametrizedUseCase<in P : Any, R> : UseCase<P, R>() {

        fun execute(params: P, o: DisposableObserver<R>) {
            doExecute(params, o)
        }
    }

    abstract class SimpleUseCase<R> : UseCase<Nothing?, R>() {

        fun execute(o: DisposableObserver<R>) {
            doExecute(null, o)
        }
    }
}

sealed class SingleUseCase<in P, R> : BaseUseCase() {

    protected abstract fun buildSingle(params: P): Single<R>

    protected fun doExecute(params: P, o: DisposableSingleObserver<R>) {
        cancel()
        disposable = o
        buildSingle(params)
                .subscribeOn(schedulers[SchedulerType.WORK])
                .observeOn(schedulers[SchedulerType.WORK_RESULT])
                .subscribe(o)
    }

    abstract class ParametrizedUseCase<in P : Any, R> : SingleUseCase<P, R>() {

        fun execute(params: P, o: DisposableSingleObserver<R>) {
            doExecute(params, o)
        }
    }

    abstract class SimpleUseCase<R> : SingleUseCase<Nothing?, R>() {

        fun execute(o: DisposableSingleObserver<R>) {
            doExecute(null, o)
        }
    }
}

sealed class CompletableUseCase<in P> : BaseUseCase() {

    protected abstract fun buildCompletable(params: P): Completable

    protected fun doExecute(params: P, o: DisposableCompletableObserver) {
        cancel()
        disposable = o
        buildCompletable(params)
                .subscribeOn(schedulers[SchedulerType.WORK])
                .observeOn(schedulers[SchedulerType.WORK_RESULT])
                .subscribe(o)
    }

    abstract class ParametrizedUseCase<in P : Any> : CompletableUseCase<P>() {

        fun execute(params: P, o: DisposableCompletableObserver) {
            doExecute(params, o)
        }
    }

    abstract class SimpleUseCase : CompletableUseCase<Nothing?>() {

        fun execute(o: DisposableCompletableObserver) {
            doExecute(null, o)
        }
    }
}