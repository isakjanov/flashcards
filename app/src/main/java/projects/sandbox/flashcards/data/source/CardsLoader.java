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

    public CardsLoader(Context context, CardsDataSource dataSource) {
        super(context);
        mDataSource = dataSource;
    }

    @Override
    public List<FlashCard> loadInBackground() {
        return null;
    }

    @Override
    protected void onStartLoading() {
        deliverResult(mDataSource.getFlashCards());
    }
}
