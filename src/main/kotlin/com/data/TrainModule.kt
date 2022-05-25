package com.data

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

var trainModule = module {
    single { TrainData() }
}

class Main : KoinComponent {
    val trainData: TrainData by inject()
}