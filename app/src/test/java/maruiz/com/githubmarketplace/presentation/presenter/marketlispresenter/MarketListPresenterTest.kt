package maruiz.com.githubmarketplace.presentation.presenter.marketlispresenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.plugins.RxJavaPlugins
import maruiz.com.githubmarketplace.data.model.markets.Node
import maruiz.com.githubmarketplace.domain.interactor.GetMarkets
import maruiz.com.githubmarketplace.presentation.recyclerview.row.EmptyCategoryRow
import maruiz.com.githubmarketplace.presentation.recyclerview.row.MarketRow
import maruiz.com.githubmarketplace.presentation.view.fragment.MarketListView
import me.alexrs.recyclerviewrenderers.interfaces.Renderable
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class MarketListPresenterTest {

    private lateinit var presenter: MarketListPresenter

    @Mock
    private lateinit var mockedGetMarkets: GetMarkets

    @Mock
    private lateinit var mockedView: MarketListView

    @Captor
    private lateinit var marketListCaptor: ArgumentCaptor<DisposableSingleObserver<List<Node>>>

    @Captor
    private lateinit var marketRenderabeList: ArgumentCaptor<List<Renderable>>

    private val immediateScheduler = object : Scheduler() {
        override fun createWorker() = ExecutorScheduler.ExecutorWorker(Executor { it.run() })
    }

    @Before
    fun setUp() {
        RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }
        presenter = MarketListPresenter(mockedGetMarkets, "")

        presenter.onAttachView(mockedView)
        verify(mockedGetMarkets).execute(capture(marketListCaptor), any())
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun errorInTheRequest() {
        marketListCaptor.value.onError(Throwable())

        verify(mockedView).showError()
    }

    @Test
    fun categoryEmpty() {
        marketListCaptor.value.onSuccess(emptyList())

        verify(mockedView).updateRenderables(capture(marketRenderabeList))
        assertEquals(1, marketRenderabeList.value.size)
        marketRenderabeList.value.forEach {
            assertTrue(it is EmptyCategoryRow)
        }
    }

    @Test
    fun categoryOneItem() {
        val nodes = listOf(Node(name = "name", shortDescription = "shortDescription", logoUrl = "logoUrl"))
        marketListCaptor.value.onSuccess(nodes)

        verify(mockedView).updateRenderables(capture(marketRenderabeList))
        assertEquals(nodes.size, marketRenderabeList.value.size)
        checkElementInRenderableList(nodes)
    }

    private fun checkElementInRenderableList(nodes: List<Node>) {
        marketRenderabeList.value.zip(nodes).forEach {
            assertTrue(it.first is MarketRow)
            assertEquals((it.first as MarketRow).marketName, it.second.name)
            assertEquals((it.first as MarketRow).marketDescription, it.second.shortDescription)
            assertEquals((it.first as MarketRow).imageUrl, it.second.logoUrl)
        }
    }

    @Test
    fun categoryMoreThanOneItem() {
        val nodes = listOf(Node(name = "nameA", shortDescription = "shortDescriptionA", logoUrl = "logoUrlA"),
                Node(name = "nameB", shortDescription = "shortDescriptionB", logoUrl = "logoUrlB"))
        marketListCaptor.value.onSuccess(nodes)

        verify(mockedView).updateRenderables(capture(marketRenderabeList))
        assertEquals(nodes.size, marketRenderabeList.value.size)
        checkElementInRenderableList(nodes)
    }
}