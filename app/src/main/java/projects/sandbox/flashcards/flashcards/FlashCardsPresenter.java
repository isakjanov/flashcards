package projects.sandbox.flashcards.flashcards;

import java.util.List;

import projects.sandbox.flashcards.data.FlashCard;
import projects.sandbox.flashcards.data.source.CardsDataSource;

/**
 * Created on 5/29/17.
 */

public class FlashCardsPresenter implements FlashCardsContract.Presenter {

    private CardsDataSource mDataSource;
    private FlashCardsContract.View mCardsView;

    public FlashCardsPresenter(CardsDataSource dataSource, FlashCardsContract.View view) {
        mDataSource = dataSource;
        mCardsView = view;

        mCardsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCards();
    }

    @Override
    public void loadCards() {
        mDataSource.getFlashCards(new CardsDataSource.GetFlashCardsCallback() {
            @Override
            public void onCardsLoaded(List<FlashCard> flashCards) {
                mCardsView.showCards(flashCards);
            }

            @Override
            public void onDataNotAvailable() {
                mCardsView.showError();
            }
        });
    }
}
