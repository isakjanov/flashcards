package projects.sandbox.flashcards.data.source;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import projects.sandbox.flashcards.data.FlashCard;

/**
 * Created on 6/20/17.
 */

public class CardsLoader extends AsyncTaskLoader<List<FlashCard>> {

    private CardsDataSource mDataSource;

    private List<FlashCard> mCards;

    public CardsLoader(Context context, CardsDataSource dataSource) {
        super(context);
        mDataSource = dataSource;
    }

    /**
     * Load Cards in background thread
     */
    @Override
    public List<FlashCard> loadInBackground() {
        List<FlashCard> cards = mDataSource.getFlashCards();
        return cards;
    }

    @Override
    public void deliverResult(List<FlashCard> data) {
        if (isReset()) {
            if (data != null) {
                // release data
            }
        }

        mCards = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    /**
     * Handles request to start the Loader
     * */
    @Override
    protected void onStartLoading() {
        if (mCards != null) {
            deliverResult(mCards);
        }

        if (mCards == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
    }
}
