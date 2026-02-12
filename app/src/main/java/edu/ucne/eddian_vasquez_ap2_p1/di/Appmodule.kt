package edu.ucne.eddian_vasquez_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.eddian_vasquez_ap2_p1.data.database.CervezaDb
import edu.ucne.eddian_vasquez_ap2_p1.data.local.dao.CervezaDao
import edu.ucne.eddian_vasquez_ap2_p1.data.repository.CervezaRepositoryImpl
import edu.ucne.eddian_vasquez_ap2_p1.domain.repository.CervezaRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CervezaDb {
        return Room.databaseBuilder(
            context,
            CervezaDb::class.java,
            "BeerTracker.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCervezaDao(db: CervezaDb): CervezaDao {
        return db.cervezaDao()
    }

    @Provides
    @Singleton
    fun provideCervezaRepository(impl: CervezaRepositoryImpl): CervezaRepository {
        return impl
    }
}