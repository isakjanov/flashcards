package projects.sandbox.flashcards.addeditflashcard;

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

    }

    @Override
    public void updateCard() {

    }

    private boolean isNewCard() {
        return mCardId == null;
    }
}
