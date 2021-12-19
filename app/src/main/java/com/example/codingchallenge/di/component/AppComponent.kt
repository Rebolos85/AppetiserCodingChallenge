package com.example.codingchallenge.di.component

import android.app.Application
import com.example.codingchallenge.ItunesApplication
import com.example.codingchallenge.di.module.AppModule
import com.example.codingchallenge.di.module.RoomModule
import com.example.codingchallenge.di.module.ApiServiceModule
import com.example.codingchallenge.di.module.NetworkModule
import com.example.codingchallenge.di.binding.*
import com.example.codingchallenge.di.builders.ActivityBuilders
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // handle android components injection
        // handle android support lib injection
        AppModule::class,
        NetworkModule::class,
        ItuneDaoLocalBinding::class,
        RemoteBinding::class,
        RepositoryBinding::class,
        RoomModule::class,
        ApiServiceModule::class,
        ActivityBuilders::class,
        BindingBaseScheduler::class,
        RepositoryBinding::class,
        BindingMapper::class,
        AndroidSupportInjectionModule::class,

    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ItunesApplication)
}