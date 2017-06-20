package projects.sandbox.flashcards.data.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projects.sandbox.flashcards.data.FlashCard;

/**
 * Fake implementation of CardsDataSource. It is used to develop view with mock data
 */
public class FakeCardsDataSource implements CardsDataSource {

    private static FakeCardsDataSource mInstance;

    private List<FlashCard> mFlashCards;

    private FakeCardsDataSource() {
        mFlashCards = new ArrayList<>(Arrays.asList(new FlashCard[] {
                new FlashCard(1, "Single responsibility principle",
                        "Class should be responsible for only one functionality."),
                new FlashCard(2, "Open / close principle",
                        "entity should be open for extension but close for modifications."),
                new FlashCard(3, "Liskov substitutioin principle",
                        "object of superclass type should be replaceable by any object " +
                                "of subclass types."),
                new FlashCard(4, "Interface segregation principle",
                        "it's better to have single purpose interface rather than general purpose."),
                new FlashCard(5, "Dependency inversion principle", "entities must depend on " +
                        "abstractions not on concretions. It states that the high level module must" +
                        " not depend on the low level module, but they should " +
                        "depend on abstractions.")
        }));
    }

    public static FakeCardsDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new FakeCardsDataSource();
        }
        return mInstance;
    }

    @Override
    public List<FlashCard> getFlashCards() {
        return mFlashCards;
    }

    @Override
    public void getFlashCard(int cardId, GetFlashCardCallback callback) {
        if (cardId < 1 || cardId > 5) {
            callback.onDataNotAvailable();
        }
        callback.onCardLoaded(mFlashCards.get(cardId - 1));
    }

    @Override
    public void deleteFlashCard(DeleteFlashCardCallback callback) {

    }

    @Override
    public void deleteAllCards() {

    }

    @Override
    public void saveFlashCard(FlashCard card) {

    }

    @Override
    public void destroy() {

    }
}
