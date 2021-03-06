package projects.sandbox.flashcards.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import projects.sandbox.flashcards.R;
import projects.sandbox.flashcards.addeditflashcard.AddEditActivity;
import projects.sandbox.flashcards.data.FlashCard;

/**
 * Created on 5/29/17.
 */

public class FlashCardsFragment extends Fragment implements FlashCardsContract.View, View.OnClickListener {

    private FlashCardsContract.Presenter mPresenter;

    private CardsListAdapter mAdapter;

    private RecyclerView mCardsListView;

    private FloatingActionButton fab;

    private ProgressBar mProgressBar;

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

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(this);

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            mPresenter.addNewCard();
        }
    }

    @Override
    public void showCards(List<FlashCard> cards) {
        mAdapter.setCards(cards);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPreloader(boolean active) {
        if (active) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyList() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showAddNewCard() {
        Intent intent = new Intent(getActivity(), AddEditActivity.class);
        startActivityForResult(intent, AddEditActivity.ADD_CARD_REQ_CODE);
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
