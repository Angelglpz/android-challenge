package com.idealista.data.repository.common

import com.idealista.data.common.BaseDataTest
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler

open class BaseIdealistaChallengeAPIRepositoryTest : BaseDataTest() {

    val testScheduler = TestCoroutineScheduler()
    val standardTestDispatcher = StandardTestDispatcher(testScheduler)
}