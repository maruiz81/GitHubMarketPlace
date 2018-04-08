package maruiz.com.githubmarketplace.data.model

class MarketPlaceQueryModel {
    val query: String
        get() = "query {\n" +
                " \tmarketplaceListings(first:100) {\n" +
                "    pageInfo {\n" +
                "      endCursor\n" +
                "      hasNextPage\n" +
                "      hasPreviousPage\n" +
                "      startCursor\n" +
                "    }\n" +
                "    edges {\n" +
                "      node {\n" +
                "        id\n" +
                "        name\n" +
                "        logoUrl\n" +
                "        documentationUrl\n" +
                "        fullDescription\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}"
}