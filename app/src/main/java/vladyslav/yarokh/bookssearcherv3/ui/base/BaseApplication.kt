package vladyslav.yarokh.bookssearcherv3.ui.base

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import vladyslav.yarokh.bookssearcherv3.di.components.DaggerAppComponent

class BaseApplication: DaggerApplication() {

    companion object {

        private var instance: BaseApplication? = null
        private var context: Context? = null

        fun getInstance(): BaseApplication? {
            return instance
        }

        fun getContext(): Context? {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()

        context = this
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}