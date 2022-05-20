package com.example.dogapp.view;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogapp.AppDatabase;
import com.example.dogapp.DogDAO;
import com.example.dogapp.viewmodel.DogAdapter;
import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private DogsApiService apiService;
    private RecyclerView rvDog;
    private ArrayList<DogBreed> dogs;
    private DogAdapter dogAdapter;
    private AppDatabase appDatabase;
    private DogDAO dogDAO;
    private SearchView searchView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appDatabase = AppDatabase.getInstance(getContext());
        dogDAO = appDatabase.dogDAO();

        rvDog = view.findViewById(R.id.rv_dog);
        dogs = new ArrayList<DogBreed>();
        dogAdapter = new DogAdapter(dogs);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(), 2, GridLayoutManager.VERTICAL, false
        );
        rvDog.setLayoutManager(gridLayoutManager);
        rvDog.setAdapter(dogAdapter);
        apiService = new DogsApiService();
        apiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<DogBreed> dogBreeds) {
                        for(DogBreed dog: dogBreeds){
                            dogs.add(dog);
                            dogAdapter.notifyDataSetChanged();
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    dogDAO.insertAllDogs(dog);
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("DEBUG1", e.getMessage());
                    }
                });
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.main_menu, menu) ;
//        MenuItem item = menu.findItem(R.id.actionSearch);
//        SearchView searchView = (SearchView) item.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                dogAdapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                dogAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}