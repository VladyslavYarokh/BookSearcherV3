package vladyslav.yarokh.bookssearcherv3.ui.fragments.search_fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mindorks.editdrawabletext.DrawablePosition
import com.mindorks.editdrawabletext.OnDrawableClickListener
import kotlinx.android.synthetic.main.fragment_search.*
import vladyslav.yarokh.bookssearcherv3.BuildConfig
import vladyslav.yarokh.bookssearcherv3.Constants
import vladyslav.yarokh.bookssearcherv3.R
import vladyslav.yarokh.bookssearcherv3.di.ViewModelProviderFactory
import vladyslav.yarokh.bookssearcherv3.ui.base.BaseFragment
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(SearchViewModel::class.java)

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.version.postValue(BuildConfig.VERSION_CODE.toString())

        initViews()
        initListeners()
    }

    private fun initViews() {
        viewModel.getVersionCode().observe(requireActivity(), Observer {
            tv_version.text = getString(R.string.app_version, it)
        })

        viewModel.getSearchQuery().observe(requireActivity(), Observer {
            et_search.setText(it, TextView.BufferType.EDITABLE)
        })
    }

    private fun initListeners() {
        et_search.setDrawableClickListener(object : OnDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                val bundle = Bundle()
                if (TextUtils.isEmpty(et_search.text)) et_search.error =
                    getString(R.string.empty_text_error)
                else {
                    viewModel.searchQuery.postValue(et_search.text.toString())
                    bundle.putString(Constants.BUNDLE_ET_FIELD_TEXT, et_search.text.toString())
                    closeKeyboard()
                    when (target) {
                        DrawablePosition.RIGHT -> findNavController().navigate(
                            R.id.action_searchFragment_to_booksFragment,
                            bundle
                        )
                    }
                }
            }
        })
    }
}