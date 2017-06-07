package projects.sandbox.flashcards.addeditflashcard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import projects.sandbox.flashcards.R;

/**
 * Created on 6/5/17.
 */

public class AddEditCardFragment extends Fragment
        implements AddEditCardContract.View, View.OnClickListener {

    private static AddEditCardFragment mInstance;

    private AddEditCardContract.Presenter mPresenter;

    private EditText mTermView;

    private EditText mDescriptionView;

    private FloatingActionButton mFab;

    public static AddEditCardFragment getInstance() {
        if (mInstance == null) {
            mInstance = new AddEditCardFragment();
        }
        return mInstance;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit, container, false);
        mTermView = (EditText) root.findViewById(R.id.term);
        mDescriptionView = (EditText) root.findViewById(R.id.description);
        mFab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mFab.setOnClickListener(this);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            mPresenter.saveCard(mTermView.getText().toString(), mDescriptionView.getText().toString());
        }
    }

    @Override
    public void setPresenter(AddEditCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setTerm() {

    }

    @Override
    public void setDescription() {

    }

    @Override
    public void closeScreen() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showTermValidationError() {

    }
}
