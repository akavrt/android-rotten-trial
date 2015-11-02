package com.akavrt.rotten;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final int MSG_REFRESH_DATA = 1;
    private static final int REFRESH_DATA_DELAY_IN_MILLIS = 1000;

    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayoutManager mLayoutManager;
//    private StaggeredGridLayoutManager mLayoutManager;

    private MovieAdapter mAdapter;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setupViews();

        mHandler = new MessageHandler(this);
    }

    private void setupViews() {
        setupToolbar();

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                handleRefresh();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration decoration = new MarginDecoration(
                getResources().getDimensionPixelSize(R.dimen.padding_half),
                getResources().getDimensionPixelSize(R.dimen.padding_quarter));
        recyclerView.addItemDecoration(decoration);

        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        MovieList list = Utils.createSampleMovieList();
        mAdapter = new MovieAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.toolbar_title);
        toolbar.setSubtitle(R.string.toolbar_subtitle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacksAndMessages(null);
    }

    private void onDataLoaded() {
        Movie movie = Utils.createSampleMovie();
        movie.title = String.format("#%d %s", mAdapter.getItemCount() + 1, movie.title);

        mAdapter.addMovie(movie);
        mLayoutManager.scrollToPosition(0);

        mRefreshLayout.setRefreshing(false);
    }

    private void handleRefresh() {
        mHandler.sendEmptyMessageDelayed(MSG_REFRESH_DATA, REFRESH_DATA_DELAY_IN_MILLIS);
    }

    private static class MessageHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MessageHandler(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity == null) {
                return;
            }

            switch (msg.what) {
                case MSG_REFRESH_DATA:
                    activity.onDataLoaded();
                    break;
            }
        }
    }
}
