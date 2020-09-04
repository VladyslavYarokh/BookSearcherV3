package vladyslav.yarokh.bookssearcherv3.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vladyslav.yarokh.bookssearcherv3.ui.fragments.books_fragment.BooksFragment
import vladyslav.yarokh.bookssearcherv3.ui.fragments.search_fragment.SearchFragment

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector(modules = [MainVMModule::class])
    abstract fun contributeFragmentLogin(): BooksFragment

    @ContributesAndroidInjector(modules = [MainVMModule::class])
    abstract fun contributeFragmentFavourites(): SearchFragment
}