package projects.sandbox.flashcards.data.source.local;

import android.provider.BaseColumns;

/**
 * Describes tables structure
 */
public final class FlashCardsContract {

    private FlashCardsContract() {}

    public static class FlashCardsEntry implements BaseColumns {
        public static final String TABLE_NAME = "flashcards";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TERM = "term";
        public static final String COLUMN_NAME_DESCRIPTION = "description";

    }
}
