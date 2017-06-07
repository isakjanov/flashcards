package projects.sandbox.flashcards.data.source;

import java.util.List;

import projects.sandbox.flashcards.data.FlashCard;

/**
 * Interface to access flashcards data
 */
public interface CardsDataSource {

    interface GetFlashCardsCallback {

        void onCardsLoaded(List<FlashCard> flashCards);

        void onDataNotAvailable();
    }

    interface GetFlashCardCallback {

        void onCardLoaded(FlashCard flashCard);

        void onDataNotAvailable();
    }

    interface DeleteFlashCardCallback {

        void onCardDeleted(FlashCard flashCard);

        void onFailedToDeleteCard();
    }

    void getFlashCards(GetFlashCardsCallback callback);

    void getFlashCard(int cardId, GetFlashCardCallback callback);

    void deleteAllCards();

    void deleteFlashCard(DeleteFlashCardCallback callback);

    void saveFlashCard(FlashCard card);

    void destroy();
}
