package com.example.linkdevtask.ui.home
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.linkdevtask.store.repository.HomeScreenRepository

class HomeScreenViewModelFactory(
    private val application: Application,
    private val mHomeScreenRepository: HomeScreenRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(
                application,
                mHomeScreenRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}