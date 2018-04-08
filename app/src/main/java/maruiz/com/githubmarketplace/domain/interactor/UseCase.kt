package maruiz.com.githubmarketplace.domain.interactor

import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a Use Case (UseCase in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 *
 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<OriginalType, ReturnType, Param> {

    private var disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    protected abstract fun buildUseCaseObservable(params: Param): Single<OriginalType>

    /**
     * Executes the current use case.

     * @param observer [DisposableObserver] which will be listening to the observable build
     * *                 by [.buildUseCaseObservable] ()} method.
     * *
     * @param params   Parameters used to build execute this use case.
     */
    fun execute(observer: DisposableSingleObserver<ReturnType>, params: Param) {
        val observable = this.buildUseCaseObservable(params).map { convertData(it) }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith<DisposableSingleObserver<ReturnType>>(observer))
    }

    abstract fun convertData(originalData: OriginalType): ReturnType

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        if (disposables.isDisposed) {
            disposables = CompositeDisposable()
        }
        disposables.add(disposable)
    }
}
