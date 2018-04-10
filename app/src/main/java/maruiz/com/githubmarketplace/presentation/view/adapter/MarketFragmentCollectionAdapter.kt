package maruiz.com.githubmarketplace.presentation.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import maruiz.com.githubmarketplace.presentation.model.Category
import maruiz.com.githubmarketplace.presentation.view.fragment.MarketListFragment

class MarketFragmentCollectionAdapter(fragmentManager: FragmentManager,
                                      private val categories: List<Category>) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = MarketListFragment.newInstance(categories[position])

    override fun getCount(): Int = categories.size

    override fun getPageTitle(position: Int): CharSequence? = categories[position].name
}