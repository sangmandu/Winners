package com.example.winners_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.winners_app.R;
import com.example.winners_app.models.Cat;

public class GalleryItemFragment extends Fragment {

    // widgets
    private ImageView mImage;

    // vars
    private Cat mCat;

    public static GalleryItemFragment getInstance(Cat cat){
        GalleryItemFragment fragment = new GalleryItemFragment();

        if(cat != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("cat", cat);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mCat = getArguments().getParcelable("cat");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mImage = view.findViewById(R.id.image);
        init();
    }

    private void init(){
        if(mCat != null){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.blank_photo_black)
                    .format(DecodeFormat.PREFER_ARGB_8888);

            Glide.with(getActivity())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mCat.getImage())
                    .into(mImage);
        }
    }

    public Cat getmCat() {
        return mCat;
    }

    public void setmCat(Cat mCat) {
        this.mCat = mCat;
    }

}














