package maruiz.com.githubmarketplace.presentation.presenter.mainpresenter

import android.util.Log
import io.reactivex.observers.DisposableSingleObserver
import maruiz.com.githubmarketplace.data.model.categories.CategoryModel
import maruiz.com.githubmarketplace.domain.interactor.GetCategories
import maruiz.com.githubmarketplace.presentation.model.Category
import maruiz.com.githubmarketplace.presentation.presenter.BasePresenter
import maruiz.com.githubmarketplace.presentation.view.activity.MainActivityView

class MainPresenter(private val getCategories: GetCategories) : BasePresenter<MainActivityView>() {

    override fun onAttachView(view: MainActivityView) {
        super.onAttachView(view)
        getCategories.execute(CategoryObserver(), GetCategories.Params())
    }

    override fun onDetachView() {
        super.onDetachView()
        getCategories.dispose()
    }

    private fun showCategories(categories: List<CategoryModel>) {
        view?.setCategories(categories.map { Category(it.name) })
    }

    companion object {
        private const val TAG = "MainPresenter"
    }

    inner class CategoryObserver : DisposableSingleObserver<List<CategoryModel>>() {
        override fun onSuccess(categories: List<CategoryModel>) {
            this@MainPresenter.showCategories(categories)
        }

        override fun onError(error: Throwable) {
            Log.e(TAG, "Error getting categories", error)
        }
    }
}