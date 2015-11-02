package com.akavrt.rotten;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder> {
    private final LayoutInflater mInflater;
    private final List<Movie> mMovies;

    public MovieAdapter(Context context, MovieList list) {
        this.mInflater = LayoutInflater.from(context);

        mMovies = new ArrayList<>();
        mMovies.addAll(list.movies);
    }

    public void addMovie(Movie movie) {
        mMovies.add(0, movie);
        notifyItemInserted(0);
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.movie_item, parent, false);
        return new MovieItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.bind(movie);
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
        private TextView mSynopsisText;

        public MovieItemViewHolder(View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.title);
            mYearText = (TextView) itemView.findViewById(R.id.year);
            mCriticsRatingsGroup = itemView.findViewById(R.id.ratings);
            mCriticsRatingText = (TextView) itemView.findViewById(R.id.critics_rating);
            mAudienceRatingText = (TextView) itemView.findViewById(R.id.audience_rating);
            mCastText = (TextView) itemView.findViewById(R.id.cast);
            mSynopsisText = (TextView) itemView.findViewById(R.id.synopsis);
        }

        public void bind(Movie movie) {
            mTitleText.setText(movie.title);
            mYearText.setText(Integer.toString(movie.year));

            bindRatings(movie.ratings);

            if (hasCast(movie)) {
                mCastText.setText(buildCast(movie, 2));
                mCastText.setVisibility(View.VISIBLE);
            } else {
                mCastText.setVisibility(View.GONE);
            }

            mSynopsisText.setText(movie.synopsis);
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
}
