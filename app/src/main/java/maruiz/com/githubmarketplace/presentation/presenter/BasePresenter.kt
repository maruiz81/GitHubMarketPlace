package maruiz.com.githubmarketplace.presentation.presenter

abstract class BasePresenter<T>(protected var view: T? = null) {

    open fun onAttachView(view: T) {
        this.view = view
    }

    open fun onDetachView() {
        this.view = null
    }
}