package projects.sandbox.flashcards.flashcards;

import java.util.List;

import projects.sandbox.flashcards.BasePresenter;
import projects.sandbox.flashcards.BaseView;
import projects.sandbox.flashcards.data.FlashCard;

/**
 * Created on 5/29/17.
 */

public interface FlashCardsContract {

    interface View extends BaseView<Presenter> {

        void showCards(List<FlashCard> cards);

        void showPreloader(boolean active);

        void showEmptyList();

        void showError();
    }

    interface Presenter extends BasePresenter {

        void loadCards();
    }
}
