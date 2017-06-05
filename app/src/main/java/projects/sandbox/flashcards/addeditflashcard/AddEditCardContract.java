package projects.sandbox.flashcards.addeditflashcard;

import projects.sandbox.flashcards.BasePresenter;
import projects.sandbox.flashcards.BaseView;

/**
 * Created on 6/5/17.
 */

public interface AddEditCardContract {

    interface View extends BaseView<Presenter> {

        void setTerm();

        void setDescription();

        void closeScreen();
    }

    interface Presenter extends BasePresenter {

        void saveCard(String term, String description);

        void updateCard();
    }

}
