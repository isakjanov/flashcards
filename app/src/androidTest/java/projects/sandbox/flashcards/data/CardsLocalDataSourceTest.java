package projects.sandbox.flashcards.data;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import projects.sandbox.flashcards.data.source.CardsDataSource;
import projects.sandbox.flashcards.data.source.local.CardsLocalDataSource;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Tests for local data source
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CardsLocalDataSourceTest {

    private CardsLocalDataSource mLocalDataSource;

    @Before
    public void setUp() {
        mLocalDataSource = CardsLocalDataSource.getInstance(
                InstrumentationRegistry.getTargetContext());
        mLocalDataSource.openDB();
    }

    @After
    public void cleanUp() {
        mLocalDataSource.deleteAllCards();
        mLocalDataSource.closeDB();
    }

    @Test
    public void testPreconditions() {
        assertNotNull(mLocalDataSource);
    }

    @Test
    public void getFlashCard_SuccessTest() {
        final int id = 1;
        final String term = "card";
        final String description = "card description";
        final FlashCard card = new FlashCard(id, term, description);
        mLocalDataSource.saveFlashCard(card);

        mLocalDataSource.getFlashCard(id, new CardsDataSource.GetFlashCardCallback() {
            @Override
            public void onCardLoaded(FlashCard flashCard) {
                assertTrue("Card should be valid", flashCard != null);
                assertEquals("Id should match", id, flashCard.getId());
                assertEquals("Term should match", term, flashCard.getTermin());
                assertEquals("Description should match", description, flashCard.getDescription());
            }

            @Override
            public void onDataNotAvailable() {
                fail("onDataNotAvailable should not be called");
            }
        });
    }

    @Test
    public void getFlashCard_NotFoundTest() {
        final boolean[] isCalled = { false };
        mLocalDataSource.getFlashCard(999, new CardsDataSource.GetFlashCardCallback() {
            @Override
            public void onCardLoaded(FlashCard flashCard) {
                fail("onCardLoaded should not be called");
            }

            @Override
            public void onDataNotAvailable() {
                isCalled[0] = true;
            }
        });
        assertTrue("onDataNotAvailable should be called", isCalled[0]);
    }

    @Test
    public void saveCards_RetrieveCardsTest() {
        final FlashCard card1 = new FlashCard(1, "card1", "card1 description");
        final FlashCard card2 = new FlashCard(2, "card2", "card2 description");
        final FlashCard card3 = new FlashCard(3, "card3", "card3 description");

        mLocalDataSource.saveFlashCard(card1);
        mLocalDataSource.saveFlashCard(card2);
        mLocalDataSource.saveFlashCard(card3);

        mLocalDataSource.getFlashCards(new CardsDataSource.GetFlashCardsCallback() {
            @Override
            public void onCardsLoaded(List<FlashCard> flashCards) {
                assertEquals("It's expected to retreive 3 cards", 3, flashCards.size());
                assertTrue("card1 should be in the result", flashCards.contains(card1));
                assertTrue("card2 should be in the result", flashCards.contains(card2));
                assertTrue("card3 should be in the result", flashCards.contains(card3));
            }

            @Override
            public void onDataNotAvailable() {
                fail();
            }
        });
    }
}
