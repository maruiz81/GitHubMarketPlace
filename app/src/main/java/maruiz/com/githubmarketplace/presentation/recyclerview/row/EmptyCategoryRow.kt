package maruiz.com.githubmarketplace.presentation.recyclerview.row

import maruiz.com.githubmarketplace.R
import me.alexrs.recyclerviewrenderers.interfaces.Renderable

class EmptyCategoryRow : Renderable {
    override fun getRenderableId(): Int = R.layout.empty_category_row
}