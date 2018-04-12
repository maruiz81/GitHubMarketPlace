package maruiz.com.githubmarketplace.data.model.markets

class MarketData(val data: MarketplaceListingsModel = MarketplaceListingsModel())

class MarketplaceListingsModel(val marketplaceListings: EdgesGroup = EdgesGroup())

class EdgesGroup(val edges: List<Edges> = emptyList())

class Edges(val node: Node = Node())

class Node(val id: String = "", val name: String = "", val logoUrl: String = "",
           val shortDescription: String = "", val documentationUrl: String = "",
           val fullDescription: String = "")