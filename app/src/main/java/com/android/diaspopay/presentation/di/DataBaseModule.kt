package com.android.diaspopay.presentation.di

import android.app.Application
import androidx.room.Room
import com.android.diaspopay.data.db.dao.DiaspoPayDataBase
import com.android.diaspopay.data.db.dao.MeansPaymentDAO
import com.android.diaspopay.data.db.dao.TransferDAO
import com.android.diaspopay.data.db.dao.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideUserDatabase(app: Application): DiaspoPayDataBase {
        return Room.databaseBuilder(app, DiaspoPayDataBase::class.java,"diaspo_pay_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(diaspoPayDatabase: DiaspoPayDataBase): UserDAO {
        return diaspoPayDatabase.getUserDAO()
    }

    @Singleton
    @Provides
    fun provideMeansPaymentDao(diaspoPayDatabase: DiaspoPayDataBase): MeansPaymentDAO {
        return diaspoPayDatabase.getMeansPaymentDAO()
    }

    @Singleton
    @Provides
    fun provideTransferDAO(diaspoPayDatabase: DiaspoPayDataBase): TransferDAO {
        return diaspoPayDatabase.getTransferDAO()
    }
}