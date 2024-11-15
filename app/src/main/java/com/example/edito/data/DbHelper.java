package com.example.edito.data;

import static com.example.edito.data.QuizContract.MovieEntry.KEY_ANSWER;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_ID;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_OPTA;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_OPTB;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_OPTC;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_OPTD;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_QUES;
import static com.example.edito.data.QuizContract.MovieEntry.TABLE_QUEST;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.edito.Question;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT , " + KEY_OPTD + " TEXT)";
        db.execSQL(sql);
        addQuestions(db);
    }

    private void addQuestions(SQLiteDatabase db) {
        SQLiteStatement stmt = db.compileStatement(
                "INSERT INTO " + TABLE_QUEST + " (" + KEY_QUES + ", " + KEY_ANSWER + ", " + KEY_OPTA + ", " + KEY_OPTB + ", " + KEY_OPTC + ", " + KEY_OPTD + ") VALUES (?, ?, ?, ?, ?, ?)"
        );

        // Question 1
        stmt.bindString(1, "1. Which of the following option leads to the portability and security of Java?");
        stmt.bindString(2, "a)Bytecode is executed by JVM ");
        stmt.bindString(3, "b)The applet makes the Java code secure and portable");
        stmt.bindString(4, "c)Use of exception handling");
        stmt.bindString(5, "d)Dynamic binding between objects");
        stmt.bindString(6, "a)Bytecode is executed by the JVM.");
        stmt.executeInsert();

        // Question 2
        stmt.bindString(1, "2. Which of the following is not a Java features?");
        stmt.bindString(2, "a)Dynamic");
        stmt.bindString(3, "b)Architecture Neutral");
        stmt.bindString(4, "c)Use of pointers");
        stmt.bindString(5, "d)Object-oriented");
        stmt.bindString(6, "c)Use of pointers");
        stmt.executeInsert();

        // Question 3
        stmt.bindString(1, "3. The \\u0021 article referred to as a");
        stmt.bindString(2, "a)Unicode escape sequence");
        stmt.bindString(3, "b)Octal escape");
        stmt.bindString(4, "c)Hexadecimal");
        stmt.bindString(5, "d)Line feed");
        stmt.bindString(6, "a)Unicode escape sequence");
        stmt.executeInsert();

        // Question 4
        stmt.bindString(1, "4. _____ is used to find and fix bugs in the Java programs.");
        stmt.bindString(2, "a)JVM");
        stmt.bindString(3, "b)JRE");
        stmt.bindString(4, "c)JDK");
        stmt.bindString(5, "d)JDB");
        stmt.bindString(6, "d)JDB");
        stmt.executeInsert();

        // Question 5
        stmt.bindString(1, "5. What is the return type of the hashCode() method in the Object class?");
        stmt.bindString(2, "a)object");
        stmt.bindString(3, "b)int");
        stmt.bindString(4, "c)long");
        stmt.bindString(5, "d)void");
        stmt.bindString(6, "b)int");
        stmt.executeInsert();

        stmt.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUEST, null);

        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return quesList;
    }
}