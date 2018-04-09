package maruiz.com.githubmarketplace.data.model.categories

class MarketCategoriesModel(val data: MarketPlaceCategories)

class MarketPlaceCategories(val marketplaceCategories: List<Category>)

class Category(val name: String)