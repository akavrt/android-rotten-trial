package com.akavrt.rotten;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_MOVIE = 1;

    private final LayoutInflater mInflater;
    private final List<Movie> mMovies;
    private final SparseBooleanArray mCollapsedStatus;

    private boolean mUseHeader;

    public MovieAdapter(Context context, MovieList list, boolean useHeader) {
        this.mInflater = LayoutInflater.from(context);
        this.mCollapsedStatus = new SparseBooleanArray();

        this.mUseHeader = useHeader;

        mMovies = new ArrayList<>();
        if (mUseHeader) {
            mMovies.add(null);
        }
        mMovies.addAll(list.movies);
    }

    public void addMovie(Movie movie) {
        int firstDataItemIndex = mUseHeader ? 1 : 0;

        sanitizeItemCollapsedStatus(mCollapsedStatus, firstDataItemIndex);
        mMovies.add(firstDataItemIndex, movie);

        notifyItemInserted(firstDataItemIndex);
    }

    private static void sanitizeItemCollapsedStatus(SparseBooleanArray collapsedStatus, int firstDataItemIndex) {
        int currentSize = collapsedStatus.size();
        SparseBooleanArray holder = new SparseBooleanArray(currentSize + 1);
        for (int i = 0; i < currentSize; i++) {
            int position = collapsedStatus.keyAt(i);
            boolean collapsed = collapsedStatus.valueAt(i);

            holder.put(position + 1, collapsed);
        }

        holder.put(firstDataItemIndex, true);

        collapsedStatus.clear();
        for (int i = 0; i < holder.size(); i++) {
            collapsedStatus.append(holder.keyAt(i), holder.valueAt(i));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mUseHeader && position == 0
                ? VIEW_TYPE_HEADER
                : VIEW_TYPE_MOVIE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == VIEW_TYPE_HEADER) {
            View itemView = mInflater.inflate(R.layout.header, parent, false);
            holder = new HeaderViewHolder(itemView);
        } else {
            View itemView = mInflater.inflate(R.layout.movie_item, parent, false);
            holder = new MovieItemViewHolder(itemView);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_MOVIE) {
            Movie movie = mMovies.get(position);
            ((MovieItemViewHolder) holder).bind(movie, mCollapsedStatus, position);
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class MovieItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleText;
        private TextView mYearText;
        private View mCriticsRatingsGroup;
        private TextView mCriticsRatingText;
        private TextView mAudienceRatingText;
        private TextView mCastText;
        private ExpandableTextView mSynopsisText;

        public MovieItemViewHolder(View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.title);
            mYearText = (TextView) itemView.findViewById(R.id.year);
            mCriticsRatingsGroup = itemView.findViewById(R.id.ratings);
            mCriticsRatingText = (TextView) itemView.findViewById(R.id.critics_rating);
            mAudienceRatingText = (TextView) itemView.findViewById(R.id.audience_rating);
            mCastText = (TextView) itemView.findViewById(R.id.cast);
            mSynopsisText = (ExpandableTextView) itemView.findViewById(R.id.synopsis);
        }

        public void bind(Movie movie, SparseBooleanArray collapsedStatus, int position) {
            mTitleText.setText(movie.title);
            mYearText.setText(Integer.toString(movie.year));

            bindRatings(movie.ratings);

            if (hasCast(movie)) {
                mCastText.setText(buildCast(movie, 2));
                mCastText.setVisibility(View.VISIBLE);
            } else {
                mCastText.setVisibility(View.GONE);
            }

            mSynopsisText.setText(movie.synopsis, collapsedStatus, position);
        }

        private static boolean hasCast(Movie movie) {
            return !movie.cast.isEmpty();
        }

        private void bindRatings(Ratings ratings) {
            if (ratings == null ||
                    !isValidRating(ratings.critics_score) && !isValidRating(ratings.audience_score)) {
                mCriticsRatingsGroup.setVisibility(View.GONE);
                return;
            }

            bindRating(ratings.critics_score, mCriticsRatingText,
                    R.drawable.ic_ratings_fresh, R.drawable.ic_ratings_rotten);
            bindRating(ratings.audience_score, mAudienceRatingText,
                    R.drawable.ic_ratings_popcorn, R.drawable.ic_ratings_spilt);

            mCriticsRatingsGroup.setVisibility(View.VISIBLE);
        }

        private static boolean isValidRating(int rating) {
            return rating > 0;
        }

        private static void bindRating(int rating, TextView ratingView,
                                       @DrawableRes int positiveRatingDrawable,
                                       @DrawableRes int negativeRatingDrawable) {
            if (!isValidRating(rating)) {
                ratingView.setVisibility(View.GONE);
                return;
            }

            ratingView.setText(String.format("%d%%", rating));
            ratingView.setCompoundDrawablesWithIntrinsicBounds(
                    isPositiveRating(rating)
                            ? positiveRatingDrawable
                            : negativeRatingDrawable, 0, 0, 0);

            ratingView.setVisibility(View.VISIBLE);
        }

        private static boolean isPositiveRating(int rating) {
            return rating >= 60;
        }

        private static String buildCast(Movie movie, int limit) {
            StringBuilder builder = new StringBuilder();
            int current = 0;
            while (current < movie.cast.size() && current < limit) {
                builder.append(movie.cast.get(current).name);
                if (current < limit - 1 && current < movie.cast.size() - 1) {
                    builder.append(", ");
                }

                current++;
            }

            return builder.toString();
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
