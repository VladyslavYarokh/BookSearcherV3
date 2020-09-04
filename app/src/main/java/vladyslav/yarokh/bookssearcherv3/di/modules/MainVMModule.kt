package vladyslav.yarokh.bookssearcherv3.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import vladyslav.yarokh.bookssearcherv3.di.VMKey
import vladyslav.yarokh.bookssearcherv3.ui.fragments.books_fragment.BooksViewModel
import vladyslav.yarokh.bookssearcherv3.ui.fragments.search_fragment.SearchViewModel


@Module
abstract class MainVMModule {

    @Binds
    @IntoMap
    @VMKey(BooksViewModel::class)
    abstract fun postLobbyViewModel(viewModel: BooksViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(SearchViewModel::class)
    abstract fun postRecoveryViewModel(viewModel: SearchViewModel): ViewModel

}