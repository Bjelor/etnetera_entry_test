package com.bjelor.sportify.di

import com.bjelor.sportify.ResultListViewModel
import com.bjelor.sportify.data.local.SportEntryLocalDataSource
import com.bjelor.sportify.data.remote.SportEntryRemoteDataSource
import com.bjelor.sportify.domain.SportEntryLocalMapper
import com.bjelor.sportify.domain.SportEntryRepository
import com.bjelor.sportify.domain.usecase.GetSportEntriesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { ResultListViewModel(get()) }

    // region Data Source

    single { SportEntryLocalDataSource(get(), get()) }
    single { SportEntryRemoteDataSource() }

    // endregion Data Source

    // region Mapper

    single { SportEntryLocalMapper() }

    // endregion Mapper

    // region Use Case

    factory { GetSportEntriesUseCase(get()) }

    // endregion Use Case

    single { SportEntryRepository(get(), get()) }

}
