package Database1;

import android.provider.BaseColumns;

public final class User {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private User() {}

    /* Inner class that defines the table contents */
    public static class userTable implements BaseColumns {
        public static final String TABLE_NAME = "Registered";
        public static final String COLUMN_1 = "username";
        public static final String COLUMN_2 = "password";
        public static final String COLUMN_3 = "dob";
    }
}