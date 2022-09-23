package com.bjelor.sportify.di

import com.bjelor.sportify.data.local.SportEntryLocalDataSource
import com.bjelor.sportify.data.local.mapper.SportEntryFormLocalMapper
import com.bjelor.sportify.data.local.mapper.SportEntryLocalMapper
import com.bjelor.sportify.data.remote.SportEntryRemoteDataSource
import com.bjelor.sportify.domain.SportEntryRepository
import com.bjelor.sportify.domain.usecase.GetSportEntriesUseCase
import com.bjelor.sportify.domain.usecase.SaveLocalSportEntryUseCase
import com.bjelor.sportify.domain.usecase.SaveRemoteSportEntryUseCase
import com.bjelor.sportify.ui.addentry.AddEntryViewModel
import com.bjelor.sportify.ui.addentry.durationpicker.DurationPickerViewModel
import com.bjelor.sportify.ui.resultlist.ResultListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { ResultListViewModel(get()) }
    viewModel { params -> DurationPickerViewModel(params.get()) }
    viewModel { AddEntryViewModel(get(), get()) }

    // region Data Source

    single { SportEntryLocalDataSource(get(), get(), get()) }
    single { SportEntryRemoteDataSource() }

    // endregion Data Source

    // region Mapper

    single { SportEntryLocalMapper() }
    single { SportEntryFormLocalMapper() }

    // endregion Mapper

    // region Use Case

    factory { GetSportEntriesUseCase(get()) }
    factory { SaveLocalSportEntryUseCase(get()) }
    factory { SaveRemoteSportEntryUseCase(get()) }

    // endregion Use Case

    single { SportEntryRepository(get(), get(), get()) }

    single { Dispatchers }

}
