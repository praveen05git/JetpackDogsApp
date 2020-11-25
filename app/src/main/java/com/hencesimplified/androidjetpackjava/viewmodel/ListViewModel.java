package com.hencesimplified.androidjetpackjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hencesimplified.androidjetpackjava.model.DogBreed;
import com.hencesimplified.androidjetpackjava.model.DogsApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<DogBreed>> dogs = new MutableLiveData<>();
    public MutableLiveData<Boolean> dogLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private DogsApiService dogsApiService = new DogsApiService();
    private CompositeDisposable disposable = new CompositeDisposable();

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    /*
    public void refresh() {
        DogBreed dog1 = new DogBreed("1", "dog1", "12", "", "", "", "");
        DogBreed dog2 = new DogBreed("2", "dog2", "13", "", "", "", "");
        DogBreed dog3 = new DogBreed("3", "dog3", "14", "", "", "", "");

        ArrayList<DogBreed> dogsList = new ArrayList<>();
        dogsList.add(dog1);
        dogsList.add(dog2);
        dogsList.add(dog3);

        dogs.setValue(dogsList);
        dogLoadError.setValue(false);
        loading.setValue(false);
    }
     */

    public void refresh() {
        fetchFromRemote();
    }

    private void fetchFromRemote() {
        loading.setValue(true);
        disposable.add(
                dogsApiService.getDogs()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                            @Override
                            public void onSuccess(@io.reactivex.annotations.NonNull List<DogBreed> dogBreeds) {
                                dogs.setValue(dogBreeds);
                                dogLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                dogLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
