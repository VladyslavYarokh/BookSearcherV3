package vladyslav.yarokh.bookssearcherv3.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import vladyslav.yarokh.bookssearcherv3.api.ApiServiceInterface
import vladyslav.yarokh.bookssearcherv3.di.modules.*
import vladyslav.yarokh.bookssearcherv3.ui.activities.MainActivity
import vladyslav.yarokh.bookssearcherv3.ui.base.BaseApplication
import vladyslav.yarokh.bookssearcherv3.ui.fragments.books_fragment.BooksFragment
import vladyslav.yarokh.bookssearcherv3.ui.fragments.search_fragment.SearchFragment

@Component (modules = arrayOf(
    NetworkModule::class,
    AppModule::class,
    AndroidInjectionModule::class,
    ActivityBuilderModule::class,
    ViewModelFactoryModule::class,
    MainVMModule::class))
interface AppComponent : AndroidInjector<BaseApplication> {

    val apiServiceInterface: ApiServiceInterface

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(booksFragment: BooksFragment)
}