package com.example.cursoandroid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.cursoandroid.core.Resource
import com.example.cursoandroid.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class movieViewModel(private val repo:MovieRepository): ViewModel() {
    //fun fetchMainScreenMovies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(Triple(repo.getUpcomingMovies(),repo.getTopRatedMovies(), repo.getPopularMovies()))) //Ntuple se crea una clase a parte debajo de todo este código
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchUpcomingMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getUpcomingMovies()))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchPopularMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getPopularMovies()))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchTopRatedMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getTopRatedMovies()))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}

class movieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}
