package projects.sandbox.flashcards.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import projects.sandbox.flashcards.data.FlashCard;
import projects.sandbox.flashcards.data.source.CardsDataSource;

/**
 * Concrete implementation of data source as db
 */

public class CardsLocalDataSource implements CardsDataSource{

    private SQLiteDatabase mDb;

    private FlashCardsDBHelper mDbHelper;

    private static CardsLocalDataSource instance;

    private CardsLocalDataSource(Context context) {
        mDbHelper = new FlashCardsDBHelper(context.getApplicationContext());
    }

    public static CardsLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new CardsLocalDataSource(context);
        }
        return instance;
    }

    @Override
    public void getFlashCards(GetFlashCardsCallback callback) {
        if (!mDb.isOpen()) {
            callback.onDataNotAvailable();
            return;
        }

        List<FlashCard> cards = new ArrayList<>();

        String[] columns = {
                FlashCardsContract.FlashCardsEntry.COLUMN_NAME_ENTRY_ID,
                FlashCardsContract.FlashCardsEntry.COLUMN_NAME_TERM,
                FlashCardsContract.FlashCardsEntry.COLUMN_NAME_DESCRIPTION
        };

        Cursor c = mDb.query(
                FlashCardsContract.FlashCardsEntry.TABLE_NAME, columns, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while(c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow(
                        FlashCardsContract.FlashCardsEntry.COLUMN_NAME_ENTRY_ID));
                String term = c.getString(c.getColumnIndexOrThrow(
                        FlashCardsContract.FlashCardsEntry.COLUMN_NAME_TERM));
                String description = c.getString(c.getColumnIndexOrThrow(
                        FlashCardsContract.FlashCardsEntry.COLUMN_NAME_DESCRIPTION));
                FlashCard card = new FlashCard(id, term, description);
                cards.add(card);
            }
        }

        if (cards.size() > 0) {
            callback.onCardsLoaded(cards);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void getFlashCard(int cardId, GetFlashCardCallback callback) {
        String[] columns = {
                FlashCardsContract.FlashCardsEntry.COLUMN_NAME_ENTRY_ID,
                FlashCardsContract.FlashCardsEntry.COLUMN_NAME_TERM,
                FlashCardsContract.FlashCardsEntry.COLUMN_NAME_DESCRIPTION
        };
        String selection = FlashCardsContract.FlashCardsEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(cardId) };
        Cursor c = mDb.query(
                FlashCardsContract.FlashCardsEntry.TABLE_NAME,
                columns, selection, selectionArgs, null, null, null);

        if (c == null || c.getCount() == 0) {
            callback.onDataNotAvailable();
            return;
        }

        c.moveToFirst();
        int id = c.getInt(
                c.getColumnIndexOrThrow(FlashCardsContract.FlashCardsEntry.COLUMN_NAME_ENTRY_ID));
        String term = c.getString(
                c.getColumnIndexOrThrow(FlashCardsContract.FlashCardsEntry.COLUMN_NAME_TERM));
        String description = c.getString(
                c.getColumnIndexOrThrow(FlashCardsContract.FlashCardsEntry.COLUMN_NAME_DESCRIPTION));

        FlashCard card = new FlashCard(id, term, description);
        callback.onCardLoaded(card);
    }

    @Override
    public void deleteAllCards() {
        mDb.delete(FlashCardsContract.FlashCardsEntry.TABLE_NAME, null, null);
    }

    @Override
    public void deleteFlashCard(DeleteFlashCardCallback callback) {

    }

    @Override
    public void saveFlashCard(FlashCard card) {
        ContentValues values = new ContentValues();
        values.put(FlashCardsContract.FlashCardsEntry.COLUMN_NAME_ENTRY_ID, card.getId());
        values.put(FlashCardsContract.FlashCardsEntry.COLUMN_NAME_TERM, card.getTermin());
        values.put(FlashCardsContract.FlashCardsEntry.COLUMN_NAME_DESCRIPTION, card.getDescription());
        mDb.insert(FlashCardsContract.FlashCardsEntry.TABLE_NAME, null, values);
    }

    public void openDB() {
        mDb = mDbHelper.getWritableDatabase();
    }

    public void closeDB() {
        if (mDb != null) {
            mDb.close();
        }
    }
}
