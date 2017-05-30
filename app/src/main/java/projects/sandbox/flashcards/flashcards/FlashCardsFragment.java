package projects.sandbox.flashcards.flashcards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import projects.sandbox.flashcards.R;
import projects.sandbox.flashcards.data.FlashCard;

/**
 * Created on 5/29/17.
 */

public class FlashCardsFragment extends Fragment implements FlashCardsContract.View {

    private FlashCardsContract.Presenter mPresenter;

    private CardsListAdapter mAdapter;

    private RecyclerView mCardsListView;

    public static FlashCardsFragment getInstance() {
        return new FlashCardsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new CardsListAdapter(new ArrayList<FlashCard>());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(FlashCardsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_flash_cards, container, false);
        mCardsListView = (RecyclerView) root.findViewById(R.id.cards_list);
        mCardsListView.setAdapter(mAdapter);
        mCardsListView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void showCards(List<FlashCard> cards) {
        mAdapter.setCards(cards);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPreloader(boolean active) {

    }

    @Override
    public void showEmptyList() {

    }

    @Override
    public void showError() {

    }

    private class CardsListAdapter extends RecyclerView.Adapter<CardsListViewHolder> {

        private List<FlashCard> mCards;

        public CardsListAdapter(List<FlashCard> cards) {
            mCards = cards;
        }

        public void setCards(List<FlashCard> cards) {
            mCards = cards;
        }

        @Override
        public CardsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_flash_cards_item, parent, false);
            CardsListViewHolder vh = new CardsListViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(CardsListViewHolder holder, int position) {
            FlashCard card = mCards.get(position);
            holder.mCardTerm.setText(card.getTermin());
        }

        @Override
        public int getItemCount() {
            return mCards.size();
        }
    }

    private class CardsListViewHolder extends RecyclerView.ViewHolder {

        public TextView mCardTerm;

        public CardsListViewHolder(View itemView) {
            super(itemView);
            mCardTerm = (TextView) itemView.findViewById(R.id.term);
        }
    }
}
