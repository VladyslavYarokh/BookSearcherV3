package vladyslav.yarokh.bookssearcherv3.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import vladyslav.yarokh.bookssearcherv3.di.ViewModelProviderFactory
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {
    @Singleton
    @Binds
    abstract fun bindVMFactory(modelProviderFactory: ViewModelProviderFactory) : ViewModelProvider.Factory
}