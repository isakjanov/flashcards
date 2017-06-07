package projects.sandbox.flashcards.flashcarditem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import projects.sandbox.flashcards.addeditflashcard.AddEditCardFragment;
import projects.sandbox.flashcards.addeditflashcard.AddEditCardPresenter;
import projects.sandbox.flashcards.data.FlashCard;
import projects.sandbox.flashcards.data.source.CardsDataSource;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created on 6/7/17.
 */

public class AddEditCardPresenterTest {

    @Mock
    private CardsDataSource mDataSource;

    @Mock
    private AddEditCardFragment mFragment;

    private AddEditCardPresenter mPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new AddEditCardPresenter(null, mFragment, mDataSource);
    }

    @Test
    public void saveCard_ValidTest() {
        mPresenter.saveCard("term", "description");

        ArgumentCaptor<FlashCard> saveCardArgumentCaptor = ArgumentCaptor.forClass(FlashCard.class);
        verify(mDataSource, times(1)).saveFlashCard(saveCardArgumentCaptor.capture());

        assertEquals("Term should match", "term", saveCardArgumentCaptor.getValue().getTermin());
        assertEquals("Description should be the same", "description",
                saveCardArgumentCaptor.getValue().getDescription());
        assertEquals("id should be null", null, saveCardArgumentCaptor.getValue().getId());

        verify(mFragment, times(1)).closeScreen();
        verify(mFragment, never()).showTermValidationError();
    }

    @Test
    public void saveCard_EmptyTermTest() {
        mPresenter.saveCard("", "description");

        ArgumentCaptor<FlashCard> saveCardArgumentCaptor = ArgumentCaptor.forClass(FlashCard.class);
        verify(mDataSource, never()).saveFlashCard(saveCardArgumentCaptor.capture());

        verify(mFragment, never()).closeScreen();
        verify(mFragment, times(1)).showTermValidationError();
    }
}
