package maruiz.com.githubmarketplace.presentation.recyclerview.factory

import maruiz.com.githubmarketplace.R
import maruiz.com.githubmarketplace.presentation.recyclerview.renderer.EmptyCategoryRenderer
import maruiz.com.githubmarketplace.presentation.recyclerview.renderer.MarketRenderer
import me.alexrs.recyclerviewrenderers.interfaces.RendererFactory
import me.alexrs.recyclerviewrenderers.renderer.Renderer

class MarketFactory : RendererFactory {
    override fun getRenderer(id: Int): Renderer = when (id) {
        R.layout.market_row -> MarketRenderer(id)
        R.layout.empty_category_row -> EmptyCategoryRenderer(id)
        else -> EmptyCategoryRenderer(id)
    }
}