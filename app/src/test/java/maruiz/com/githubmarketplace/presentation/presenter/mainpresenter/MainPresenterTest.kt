package maruiz.com.githubmarketplace.presentation.presenter.mainpresenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.plugins.RxJavaPlugins
import maruiz.com.githubmarketplace.data.model.categories.CategoryModel
import maruiz.com.githubmarketplace.domain.interactor.GetCategories
import maruiz.com.githubmarketplace.presentation.model.Category
import maruiz.com.githubmarketplace.presentation.view.activity.MainActivityView
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    private lateinit var presenter: MainPresenter

    @Mock
    private lateinit var mockedGetCategories: GetCategories

    @Mock
    private lateinit var mockedView: MainActivityView

    @Captor
    private lateinit var captorLisCategory: ArgumentCaptor<DisposableSingleObserver<List<CategoryModel>>>

    @Captor
    private lateinit var captorLisCategoryClient: ArgumentCaptor<List<Category>>

    private val immediateScheduler = object : Scheduler() {
        override fun createWorker() = ExecutorScheduler.ExecutorWorker(Executor { it.run() })
    }

    @Before
    fun setUp() {
        RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }
        presenter = MainPresenter(mockedGetCategories)

        presenter.onAttachView(mockedView)
        verify(mockedGetCategories).execute(capture(captorLisCategory), any())
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun errorGettingCategories() {
        captorLisCategory.value.onError(Throwable())
        verify(mockedView).showError()
    }

    @Test
    fun categoryListEmpty() {
        captorLisCategory.value.onSuccess(emptyList())
        verify(mockedView).setCategories(capture(captorLisCategoryClient))
        assertEquals(0, captorLisCategoryClient.value.size)
    }

    @Test
    fun categoryListWithItems() {
        val categories = listOf(CategoryModel("nameA", "slugA"),
                CategoryModel("nameB", "slugB"))
        captorLisCategory.value.onSuccess(categories)
        verify(mockedView).setCategories(capture(captorLisCategoryClient))
        assertEquals(2, captorLisCategoryClient.value.size)
        captorLisCategoryClient.value.zip(categories).forEach {
            assertEquals(it.first.name, it.second.name)
            assertEquals(it.first.slug, it.second.slug)
        }
    }
}