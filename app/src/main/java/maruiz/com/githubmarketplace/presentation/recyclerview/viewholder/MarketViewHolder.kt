package maruiz.com.githubmarketplace.presentation.recyclerview.viewholder

import android.view.View
import kotlinx.android.synthetic.main.market_row.view.*
import maruiz.com.githubmarketplace.presentation.recyclerview.row.MarketRow
import maruiz.com.githubmarketplace.presentation.utils.loadUrl
import me.alexrs.recyclerviewrenderers.viewholder.RenderViewHolder

class MarketViewHolder(private val view: View) : RenderViewHolder<MarketRow>(view) {
    override fun onBindView(row: MarketRow) {
        view.title.text = row.marketName
        view.description.text = row.marketDescription
        view.logo.loadUrl(row.imageUrl)
    }
}