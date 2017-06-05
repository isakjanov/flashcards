package projects.sandbox.flashcards.addeditflashcard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import projects.sandbox.flashcards.R;

/**
 * Created on 6/5/17.
 */

public class AddEditCardFragment extends Fragment implements AddEditCardContract.View {

    private static AddEditCardFragment mInstance;

    private AddEditCardContract.Presenter mPresenter;

    private EditText mTermView;

    private EditText mDescriptionView;

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
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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

    }
}
