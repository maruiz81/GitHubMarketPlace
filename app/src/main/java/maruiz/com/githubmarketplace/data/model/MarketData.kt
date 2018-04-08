package maruiz.com.githubmarketplace.data.model

class MarketData(val data: EdgesGroup = EdgesGroup())

class EdgesGroup(val edges: List<Edges> = emptyList())

class Edges(val node: Node = Node())

class Node(val id: String = "", val name: String = "", val logoUrl: String = "",
           val shortDescription: String = "", val documentationUrl: String = "",
           val fullDescription: String = "")