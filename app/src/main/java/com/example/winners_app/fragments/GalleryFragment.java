package com.example.winners_app.fragments;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.winners_app.MainActivity;
import com.example.winners_app.OnBackPressedListener;
import com.example.winners_app.R;
import com.example.winners_app.adapter.StaggeredRecyclerViewAdapter;
import com.example.winners_app.models.Cat;
import com.example.winners_app.resources.Cats;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class GalleryFragment extends Fragment implements SearchView.OnQueryTextListener, OnBackPressedListener {

    private static final String TAG = "MainActivity";
    private static final int NUM_COLUMNS = 3;

    private FloatingActionButton fab;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();

    static String query = "";

    private int centerX;
    private int centerY;
    private int radius;

    public static GalleryFragment newInstance() {
        GalleryFragment galleryFragment = new GalleryFragment();
        return galleryFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 상태표시줄 숨기기
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        recyclerView = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.FAB);
        searchView = view.findViewById(R.id.search_bar);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if ((dy != 0 && fab.isShown()) && searchView.getVisibility() == View.INVISIBLE){
                    Animation aniFade = AnimationUtils.loadAnimation(getContext(),R.anim.slide_right_out_fab);
                    fab.startAnimation(aniFade);
                    fab.setVisibility(View.INVISIBLE);
                }
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && searchView.getVisibility() == View.INVISIBLE && !fab.isShown()){
                    Animation aniFade = AnimationUtils.loadAnimation(getContext(),R.anim.slide_left_in_fab);
                    fab.startAnimation(aniFade);
                    fab.setVisibility(View.VISIBLE);
                }
            }

        });

        initImageBitmaps();

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                centerX = (int) fab.getX() + fab.getWidth() / 2;
                centerY = (int) fab.getY() + fab.getHeight() / 2;
                radius = (int) Math.hypot(searchView.getWidth(), searchView.getHeight());

                if (searchView.getVisibility() == View.INVISIBLE){
                    fab.setImageResource(R.drawable.ic_close);
                    Animator revealAnimator = ViewAnimationUtils.createCircularReveal(searchView, centerX, centerY, 0, radius);
                    revealAnimator.setDuration(100);
                    searchView.setVisibility(View.VISIBLE);
                    revealAnimator.start();
                    searchView.setIconifiedByDefault(true);
                    searchView.setFocusable(true);
                    searchView.setIconified(false);
                }

                else if (searchView.getVisibility() == View.VISIBLE){
                    fab.setImageResource(R.drawable.ic_search);
                    Animator revealAnimator = ViewAnimationUtils.createCircularReveal(searchView, centerX, centerY, radius, 0);
                    revealAnimator.addListener(mRevealAnimatorListener);
                    revealAnimator.setDuration(100);
                    revealAnimator.start();
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (searchView.getVisibility() == View.VISIBLE) {
                    fab.setImageResource(R.drawable.ic_search);
                    Animator revealAnimator = ViewAnimationUtils.createCircularReveal(searchView, centerX, centerY, radius, 0);
                    revealAnimator.addListener(mRevealAnimatorListener);
                    revealAnimator.setDuration(100);
                    revealAnimator.start();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                query = s;
                final String lowerCaseQuery = query.toLowerCase();
                Cat[] cats = Cats.getCats();
                mImageUrls.clear();
                mNames.clear();
                for(Cat cat: cats){
                    if (cat.getTitle().toLowerCase().contains(lowerCaseQuery)) {
                        mImageUrls.add(cat.getImage());
                        mNames.add(cat.getTitle());
                    }
                }
                initRecyclerView();
                return true;
            }
        });
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        Cat[] cats = Cats.getCats();
        for(Cat cat: cats){
            mImageUrls.add(cat.getImage());
            mNames.add(cat.getTitle());
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: initializing staggered recyclerview.");

        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter = new StaggeredRecyclerViewAdapter(getContext(), mNames, mImageUrls);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }

    // 애미메이션 끝날 때까지 기다리기
    private Animator.AnimatorListener mRevealAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) { searchView.setVisibility(View.INVISIBLE); }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }

        @Override
        public void onAnimationStart(Animator animation, boolean isReverse) {
        }

        @Override
        public void onAnimationEnd(Animator animation, boolean isReverse) {
        }
    };

    // 검색 수정시, 필터 적용
    @Override
    public boolean onQueryTextChange(String query) {
        final String lowerCaseQuery = query.toLowerCase();
        Cat[] cats = Cats.getCats();
        for(Cat cat: cats){
            if (cat.getTitle().contains(lowerCaseQuery)) {
                mImageUrls.add(cat.getImage());
                mNames.add(cat.getTitle());
            }
        }
        initRecyclerView();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    public void GalleryBackPressed() {
        if (searchView.getVisibility() == View.VISIBLE) {
            fab.setImageResource(R.drawable.ic_search);
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(searchView, centerX, centerY, radius, 0);
            revealAnimator.addListener(mRevealAnimatorListener);
            revealAnimator.setDuration(100);
            revealAnimator.start();
        }
    }

    // 뒤로가기로 검색창 끄기
    @Override
    public boolean onBackPressed() {
        if (searchView.getVisibility() == View.VISIBLE) {
            fab.setImageResource(R.drawable.ic_search);
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(searchView, centerX, centerY, radius, 0);
            revealAnimator.addListener(mRevealAnimatorListener);
            revealAnimator.setDuration(100);
            revealAnimator.start();
            return true;
        }
        else {
            return false;
        }
    }
}
