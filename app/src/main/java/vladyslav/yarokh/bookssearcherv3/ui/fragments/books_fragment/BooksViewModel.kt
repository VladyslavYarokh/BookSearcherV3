package vladyslav.yarokh.bookssearcherv3.ui.fragments.books_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.playcus.free.vpn.unblock.proxy.octopusvpn.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import vladyslav.yarokh.bookssearcherv3.Constants
import vladyslav.yarokh.bookssearcherv3.data.DataItems
import vladyslav.yarokh.bookssearcherv3.di.components.AppComponent
import javax.inject.Inject

class BooksViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var appComponent: AppComponent
    private val dataItems = MutableLiveData<DataItems>()
    private val job: Job = Job()

    override fun onFinish() {
        job.cancel()
    }

    private suspend fun getItems(searchQuery: String) {
        CoroutineScope(Dispatchers.IO + job).launch {
            dataItems.postValue(
                appComponent.apiServiceInterface
                    .getItemsByName(
                        Constants.API_KEY,
                        searchQuery,
                        Constants.MAX_RESULTS
                    )
            )
        }
    }

    suspend fun getDataItems(searchQuery: String): LiveData<DataItems> {
        getItems(searchQuery)
        return dataItems
    }
}
