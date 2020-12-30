package com.hencesimplified.androidjetpackjava.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.palette.graphics.Palette;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hencesimplified.androidjetpackjava.R;
import com.hencesimplified.androidjetpackjava.databinding.FragmentDetailBinding;
import com.hencesimplified.androidjetpackjava.model.DogBreed;
import com.hencesimplified.androidjetpackjava.model.DogPalette;
import com.hencesimplified.androidjetpackjava.util.Util;
import com.hencesimplified.androidjetpackjava.viewmodel.DetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    private int dogUuid;
    private DetailViewModel viewModel;
    private FragmentDetailBinding binding;

    /*
    @BindView(R.id.dogImage)
    ImageView dogImage;

    @BindView(R.id.dogName)
    TextView dogName;

    @BindView(R.id.dogPurpose)
    TextView dogPurpose;

    @BindView(R.id.dogTemperament)
    TextView temperament;

    @BindView(R.id.dogLifespan)
    TextView dogLifespan;
     */

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_detail, container, false);

        //ButterKnife.bind(this, view);
        //return view;

        FragmentDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        this.binding = binding;
        ///binding.dogName.setText("Some text"); Can directly set data using binding
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            dogUuid = DetailFragmentArgs.fromBundle(getArguments()).getDogUuid();
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.fetch(dogUuid);

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.dogLiveData.observe(this, dogBreed -> {

            if (dogBreed != null && dogBreed instanceof DogBreed && getContext() != null) {
                /*
                dogName.setText(dogBreed.dogBreed);
                dogPurpose.setText(dogBreed.bredFor);
                temperament.setText(dogBreed.temperament);
                dogLifespan.setText(dogBreed.lifeSpan);

                if (dogBreed.imageUrl != null) {
                    Util.loadImage(dogImage, dogBreed.imageUrl, new CircularProgressDrawable(getContext()));
                }
                 */

                binding.setDog(dogBreed);
                if (dogBreed.imageUrl != null) {
                    setupBackgroundColor(dogBreed.imageUrl);
                }
            }
        });
    }

    private void setupBackgroundColor(String url) {
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Palette.from(resource)
                                .generate(palette -> {
                                    int intColor = palette.getLightMutedSwatch().getRgb();
                                    DogPalette myPalette = new DogPalette(intColor);
                                    binding.setPalette(myPalette);
                                });
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}