package projects.sandbox.flashcards.addeditflashcard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import projects.sandbox.flashcards.R;
import projects.sandbox.flashcards.data.source.local.CardsLocalDataSource;

public class AddEditActivity extends AppCompatActivity {

    public static final int ADD_CARD_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AddEditCardFragment addEditCardFragment = (AddEditCardFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (addEditCardFragment == null) {
            addEditCardFragment = AddEditCardFragment.getInstance();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.contentFrame, addEditCardFragment)
                    .commit();
        }

        AddEditCardPresenter presenter = new AddEditCardPresenter(null, addEditCardFragment,
                CardsLocalDataSource.getInstance(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
