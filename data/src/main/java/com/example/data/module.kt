package com.example.data

import com.example.data.di.RepositoryModule
import com.example.data.di.NetworkModule

val dataModule = listOf(
    NetworkModule,
    RepositoryModule
)