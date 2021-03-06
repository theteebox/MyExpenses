package org.totschnig.myexpenses.di

import dagger.Module
import dagger.Provides
import org.totschnig.myexpenses.MyApplication
import org.totschnig.myexpenses.activity.ImageViewIntentProvider
import org.totschnig.myexpenses.activity.SystemImageViewIntentProvider
import org.totschnig.myexpenses.preference.PrefHandler
import org.totschnig.myexpenses.ui.DiscoveryHelper
import org.totschnig.myexpenses.ui.IDiscoveryHelper
import org.totschnig.myexpenses.util.ads.AdHandlerFactory
import org.totschnig.myexpenses.util.ads.DefaultAdHandlerFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
open class UiModule {
    @Provides
    @Singleton
    fun provideImageViewIntentProvider(): ImageViewIntentProvider = SystemImageViewIntentProvider()

    @Provides
    @Singleton
    fun provideAdHandlerFactory(application: MyApplication, prefHandler: PrefHandler, @Named(AppComponent.USER_COUNTRY) userCountry: String): AdHandlerFactory = object : DefaultAdHandlerFactory(application, prefHandler, userCountry) {
        override fun isAdDisabled() = true
    }

    @Provides
    @Singleton
    open fun provideDiscoveryHelper(prefHandler: PrefHandler): IDiscoveryHelper = DiscoveryHelper(prefHandler)

}