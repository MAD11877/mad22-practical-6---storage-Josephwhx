package sg.edu.np.mad.practical3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDBHandler extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "userspace.dh";
    public static String USERSPACE = "UserSpace";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "Id";
    public static String COLUMN_FOLLOW = "FollowStatus";
    public static int DATABASE_VERSION = 1;

    public UserDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Set up database
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USERSPACE_TABLE = "CREATE TABLE " + USERSPACE +
                "(" + COLUMN_USERNAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_ID + " TINYINT," +
                COLUMN_FOLLOW + " BOOL" + ")";

        db.execSQL(CREATE_USERSPACE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USERSPACE);

        onCreate(db);
    }


    //Method to add users into database
    public void addUser(ArrayList<User> userList){
        //go through the list
        for (User user: userList) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, user.getName());
            values.put(COLUMN_DESCRIPTION, user.getDescription());
            values.put(COLUMN_ID, user.getId());
            values.put(COLUMN_FOLLOW, user.isFollowed());

            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(USERSPACE, null, values);
            db.close();
        }
    }

    //Method to get all users
    public ArrayList<User> getUsers(){
        ArrayList<User> userList = new ArrayList<>();

        String query = "SELECT * FROM " + USERSPACE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                User userData = new User();
                userData.setName(cursor.getString(0));
                userData.setDescription(cursor.getString(1));
                userData.setId(cursor.getInt(2));
                userData.setFollowed(convertFollow(cursor.getInt(3)));
                //Add to user to list
                userList.add(userData);
                cursor.moveToNext();
            }
        }
        else{
            userList = null;
        }
        db.close();
        return userList;
    }

    //Method to update user status on follow/unfollow button clicked
    public void updateUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOW, user.isFollowed());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(USERSPACE, values, COLUMN_ID + "=?", new String[]{String.valueOf(user.getId())});
        db.close();

    }

    //Method to convert string to int
    public int convertID(String idNumber){
        return Integer.parseInt(idNumber);
    }

    //Method to convert string to bool
    public boolean convertFollow(int boolNumber){
        if(boolNumber == 0){
            return false;
        }
        return true;
    }
}
