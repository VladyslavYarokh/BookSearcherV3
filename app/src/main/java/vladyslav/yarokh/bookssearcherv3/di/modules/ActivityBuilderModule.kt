package vladyslav.yarokh.bookssearcherv3.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vladyslav.yarokh.bookssearcherv3.di.MainScope
import vladyslav.yarokh.bookssearcherv3.ui.activities.MainActivity
import vladyslav.yarokh.bookssearcherv3.ui.activities.SplashActivity

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = arrayOf(
            MainFragmentBuildersModule::class
        )
    )
    abstract fun contributeMainActivity(): MainActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = arrayOf(
            MainFragmentBuildersModule::class
        )
    )
    abstract fun contributeSplashActivity(): SplashActivity
}