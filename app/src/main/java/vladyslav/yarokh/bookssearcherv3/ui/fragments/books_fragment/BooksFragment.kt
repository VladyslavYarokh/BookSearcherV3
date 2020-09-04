package vladyslav.yarokh.bookssearcherv3.ui.fragments.books_fragment

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_results.*
import kotlinx.coroutines.*
import vladyslav.yarokh.bookssearcherv3.Constants
import vladyslav.yarokh.bookssearcherv3.R
import vladyslav.yarokh.bookssearcherv3.adapters.BooksAdapter
import vladyslav.yarokh.bookssearcherv3.data.Item
import vladyslav.yarokh.bookssearcherv3.di.ViewModelProviderFactory
import vladyslav.yarokh.bookssearcherv3.ui.base.BaseFragment
import vladyslav.yarokh.bookssearcherv3.ui.dialogs.BookDialog
import javax.inject.Inject

class BooksFragment : BaseFragment(), BooksAdapter.BookItemCallback,
    BookDialog.OnDialogButtonClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: BooksViewModel
    private val job = Job()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(BooksViewModel::class.java)

        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main+job).launch {
            initRecyclerView()
        }
    }

    private suspend fun initRecyclerView() {
        viewModel.getDataItems(requireArguments().getString(Constants.BUNDLE_ET_FIELD_TEXT)!!)
            .observe(requireActivity(), Observer {
                val booksAdapter = BooksAdapter(it, requireActivity(), this)

                rv_books.apply {
                    adapter = booksAdapter
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                }
            })
    }

    override fun onItemClick(item: Item) {
        val dialog = BookDialog(
            requireActivity(),
            R.style.CustomDialog,
            this,
            item
        )
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.show()
    }

    override fun onDialogButtonClick(item: Item) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, item.volumeInfo?.infoLink)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

        job.cancel()
        viewModel.onFinish()
    }
}