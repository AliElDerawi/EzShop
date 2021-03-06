package am2.hu.ezshop.di

import am2.hu.ezshop.persistance.dao.HistoryDao
import am2.hu.ezshop.persistance.dao.ItemDao
import am2.hu.ezshop.persistance.dao.ListNameDao
import am2.hu.ezshop.persistance.db.EzDatabase
import am2.hu.ezshop.viewmodel.EzShopViewModelFactory
import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [(ViewModelSubComponent::class)])
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun providesEzDatabase(): EzDatabase =

            Room.databaseBuilder(application, EzDatabase::class.java, "ez_shop.db")
                    .build()

    @Provides
    @Singleton
    fun providesSharedPreferences(): SharedPreferences = application.getSharedPreferences("settings", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesContext(): Context = application

    @Provides
    @Singleton
    fun providesShopDao(database: EzDatabase): ListNameDao = database.listNameDao()

    @Provides
    @Singleton
    fun providesItemDao(database: EzDatabase): ItemDao = database.itemDao()

    @Provides
    @Singleton
    fun providesHistoryDao(database: EzDatabase): HistoryDao = database.historyDao()

    @Provides
    @Singleton
    fun providesViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory = EzShopViewModelFactory(viewModelSubComponent.build())

}