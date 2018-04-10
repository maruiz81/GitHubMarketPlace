package maruiz.com.githubmarketplace.presentation.view.activity

import maruiz.com.githubmarketplace.presentation.model.Category

interface MainActivityView {
    fun setCategories(categories: List<Category>)
}