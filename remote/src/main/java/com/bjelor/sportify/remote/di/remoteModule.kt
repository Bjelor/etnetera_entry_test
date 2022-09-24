package com.bjelor.sportify.remote.di

import com.bjelor.sportify.remote.RetrofitFactory
import org.koin.dsl.module

val remoteModule = module {

    single { RetrofitFactory().create() }
}
