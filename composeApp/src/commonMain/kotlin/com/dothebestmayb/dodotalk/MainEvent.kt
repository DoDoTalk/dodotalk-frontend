package com.dothebestmayb.dodotalk

sealed interface MainEvent {
    data object OnSessionExpired: MainEvent
}
