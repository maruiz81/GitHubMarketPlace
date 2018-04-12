package maruiz.com.githubmarketplace.data.model.markets

class MarketPlaceQueryModel(private val categorySlug: String,
                            val query: String = "query {marketplaceListings(first:100, categorySlug: \"$categorySlug\") {" +
                                    "edges {node {id name logoUrl shortDescription}}}}")