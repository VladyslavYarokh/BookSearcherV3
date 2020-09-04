package vladyslav.yarokh.bookssearcherv3.ui.fragments.search_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.playcus.free.vpn.unblock.proxy.octopusvpn.base.BaseViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

class SearchViewModel @Inject constructor() : BaseViewModel() {

    val version = MutableLiveData<String>()
    val searchQuery = MutableLiveData<String>()
    private val job = Job()

    override fun onFinish() {
        job.cancel()
    }

    fun getVersionCode(): LiveData<String> = version
    fun getSearchQuery(): LiveData<String> = searchQuery
}