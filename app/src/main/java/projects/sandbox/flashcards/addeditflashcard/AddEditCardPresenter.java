package projects.sandbox.flashcards.addeditflashcard;

import projects.sandbox.flashcards.data.FlashCard;
import projects.sandbox.flashcards.data.source.CardsDataSource;

/**
 * Created on 6/5/17.
 */

public class AddEditCardPresenter implements AddEditCardContract.Presenter {

    private String mCardId;

    private AddEditCardContract.View mView;

    private CardsDataSource mDataSource;

    public AddEditCardPresenter(String cardId, AddEditCardContract.View view, CardsDataSource dataSource) {
        mCardId = cardId;
        mView = view;
        mDataSource = dataSource;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (isNewCard()) {
            mView.setTerm();
            mView.setDescription();
        }
    }

    @Override
    public void saveCard(String term, String description) {
        if (isDataValid(term, description)) {
            FlashCard card = new FlashCard(term, description);
            mDataSource.saveFlashCard(card);
            mView.closeScreen();
        } else {
            mView.showTermValidationError();
        }
    }

    @Override
    public void updateCard() {

    }

    private boolean isNewCard() {
        return mCardId == null;
    }

    private boolean isDataValid(String term, String description) {
        if (term.isEmpty()) {
            return false;
        }
        return true;
    }
}
