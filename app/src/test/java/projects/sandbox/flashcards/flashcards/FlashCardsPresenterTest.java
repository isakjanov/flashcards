package projects.sandbox.flashcards.flashcards;

import android.support.v4.app.LoaderManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projects.sandbox.flashcards.data.FlashCard;
import projects.sandbox.flashcards.data.source.CardsDataSource;
import projects.sandbox.flashcards.data.source.CardsLoader;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created on 5/30/17.
 */

public class FlashCardsPresenterTest {

    private static List<FlashCard> CARDS;

    private FlashCardsPresenter mPresenter;

    @Mock
    private CardsDataSource mDataSource;

    @Mock
    private CardsLoader mLoader;

    @Mock
    private LoaderManager mLoaderManager;

    @Mock
    private FlashCardsFragment mFragment;

    @Captor
    private ArgumentCaptor<CardsDataSource.GetFlashCardsCallback> mLoadCardsCallbackCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new FlashCardsPresenter(mDataSource, mLoader, mLoaderManager, mFragment);

        CARDS = new ArrayList<>(Arrays.asList(new FlashCard[] {
                new FlashCard(1, "Single responsibility principle",
                        "Class should be responsible for only one functionality."),
                new FlashCard(2, "Open / close principle",
                        "entity should be open for extension but close for modifications."),
                new FlashCard(3, "Liskov substitution principle",
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

    @Test
    public void createPresenter_setPresenterToView() {
        mPresenter = new FlashCardsPresenter(mDataSource, mLoader, mLoaderManager, mFragment);

        // Check View.setPresenter is called when presenter is created
        verify(mFragment).setPresenter(mPresenter);
    }

    @Test
    public void loadCardsFromDataSourceAndDispayOnView() {
        mPresenter.loadCards();

        //verify(mDataSource).getFlashCards(mLoadCardsCallbackCaptor.capture());
        mLoadCardsCallbackCaptor.getValue().onCardsLoaded(CARDS);

        // Check progress indicator is shown
        InOrder inOrder = inOrder(mFragment);
        inOrder.verify(mFragment).showPreloader(true);
        // Check progress indicator is hidden and all tasks are shown in UI
        inOrder.verify(mFragment).showPreloader(false);

        ArgumentCaptor<List> showCardsArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mFragment).showCards(showCardsArgumentCaptor.capture());
        assertEquals("5 cards expected to be shown", 5, showCardsArgumentCaptor.getValue().size());
    }

    @Test
    public void loadCardsFromDataSourceAndDisplayError() {
        mPresenter.loadCards();
        //verify(mDataSource).getFlashCards(mLoadCardsCallbackCaptor.capture());
        mLoadCardsCallbackCaptor.getValue().onDataNotAvailable();

        // Check error message is shown
        verify(mFragment).showError();
    }

    @Test
    public void addFlashCard() {
        mPresenter.addNewCard();
        verify(mFragment, times(1)).showAddNewCard();
    }
}
