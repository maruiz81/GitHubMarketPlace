package maruiz.com.githubmarketplace.presentation.recyclerview.renderer

import android.view.ViewGroup
import maruiz.com.githubmarketplace.presentation.recyclerview.row.MarketRow
import maruiz.com.githubmarketplace.presentation.recyclerview.viewholder.MarketViewHolder
import maruiz.com.githubmarketplace.presentation.utils.getView
import me.alexrs.recyclerviewrenderers.renderer.Renderer
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder

class MarketRenderer(id: Int) : Renderer(id) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, id: Int): RenderViewHolder<out MarketRow> =
            MarketViewHolder(getView(viewGroup))
}