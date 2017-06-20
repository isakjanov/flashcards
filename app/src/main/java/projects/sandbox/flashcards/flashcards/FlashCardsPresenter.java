package projects.sandbox.flashcards.flashcards;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import projects.sandbox.flashcards.data.FlashCard;
import projects.sandbox.flashcards.data.source.CardsDataSource;
import projects.sandbox.flashcards.data.source.CardsLoader;

/**
 * Created on 5/29/17.
 */

public class FlashCardsPresenter implements FlashCardsContract.Presenter,
        LoaderManager.LoaderCallbacks<List<FlashCard>> {

    private CardsDataSource mDataSource;
    private FlashCardsContract.View mCardsView;
    private CardsLoader mLoader;
    private android.support.v4.app.LoaderManager mLoaderManager;

    public FlashCardsPresenter(CardsDataSource dataSource,
                               CardsLoader loader,
                               android.support.v4.app.LoaderManager loaderManager,
                               FlashCardsContract.View view) {
        mDataSource = dataSource;
        mCardsView = view;
        mLoader = loader;
        mLoaderManager = loaderManager;

        mCardsView.setPresenter(this);
    }

    @Override
    public void start() {
        mLoaderManager.initLoader(0, null, this);
    }

    @Override
    public void loadCards() {

    }

    @Override
    public void addNewCard() {
        mCardsView.showAddNewCard();
    }

    @Override
    public Loader<List<FlashCard>> onCreateLoader(int id, Bundle args) {
        mCardsView.showPreloader(true);
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<FlashCard>> loader, List<FlashCard> data) {
        mCardsView.showPreloader(false);
        mCardsView.showCards(data);
    }

    @Override
    public void onLoaderReset(Loader<List<FlashCard>> loader) {

    }
}
