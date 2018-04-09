package maruiz.com.githubmarketplace.data.model.categories

class MarketCategoriesQueryModel {
    val query: String
        get() = "marketplaceCategories {\n" +
                "    name\n" +
                "  }"
}