package maruiz.com.githubmarketplace.data.model.categories

class MarketCategoriesModel(val data: MarketPlaceCategories = MarketPlaceCategories())

class MarketPlaceCategories(val marketplaceCategories: List<CategoryModel> = emptyList())

class CategoryModel(val name: String = "", val slug: String = "")