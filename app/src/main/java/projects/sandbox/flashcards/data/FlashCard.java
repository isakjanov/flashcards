package projects.sandbox.flashcards.data;

/**
 * Immutable model class for FlashCard
 */
public final class FlashCard {

    private int mId;

    private final String mTerm;

    private final String mDescription;

    /**
     * Use this constructor to create new FlashCard
     *
     * @param term        termin of the flashcard
     * @param description description of the flashcard
     */
    public FlashCard(String term, String description) {
        mTerm = term;
        mDescription = description;
    }

    /**
     * Use this constructor to create FlashCard if it already has id (make a copy)
     *
     * @param id            id of the flashcard
     * @param term          term of the flashcard
     * @param description   desctiption of the flashcard
     */
    public FlashCard(int id, String term, String description) {
        mId = id;
        mTerm = term;
        mDescription = description;
    }

    public int getId() {
        return mId;
    }

    public String getTermin() {
        return mTerm;
    }

    public String getDescription() {
        return mDescription;
    }

    @Override
    public int hashCode() {
        return mDescription.hashCode() + mTerm.hashCode() + mId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FlashCard) {
            FlashCard fc = (FlashCard) obj;
            if (fc.getId() == this.mId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "FlashCard with termin " + mTerm;
    }
}
