package maruiz.com.githubmarketplace.presentation.recyclerview.row

import maruiz.com.githubmarketplace.R
import me.alexrs.recyclerviewrenderers.interfaces.Renderable

class MarketRow(val marketName: String, val marketDescription: String, val imageUrl: String) : Renderable {
    override fun getRenderableId(): Int = R.layout.market_row
}