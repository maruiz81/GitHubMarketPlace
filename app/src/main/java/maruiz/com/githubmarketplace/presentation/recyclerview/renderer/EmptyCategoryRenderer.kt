package maruiz.com.githubmarketplace.presentation.recyclerview.renderer

import android.view.ViewGroup
import maruiz.com.githubmarketplace.presentation.recyclerview.row.EmptyCategoryRow
import maruiz.com.githubmarketplace.presentation.recyclerview.viewholder.EmptyCategoryViewHolder
import maruiz.com.githubmarketplace.presentation.utils.getView
import me.alexrs.recyclerviewrenderers.renderer.Renderer
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder

class EmptyCategoryRenderer(id: Int) : Renderer(id) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, id: Int): RenderViewHolder<out EmptyCategoryRow> =
            EmptyCategoryViewHolder(getView(viewGroup))
}