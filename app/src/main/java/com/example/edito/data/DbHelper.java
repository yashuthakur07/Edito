package com.example.edito.data;

import static com.example.edito.data.QuizContract.MovieEntry.KEY_ANSWER;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_ID;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_OPTA;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_OPTB;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_OPTC;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_OPTD;
import static com.example.edito.data.QuizContract.MovieEntry.KEY_QUES;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.edito.Question;


public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    private static final String TABLE_QUEST = "quest"
            ;
    // tasks table name

    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT ,"+KEY_OPTD+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
        Question q1=new Question("1. Which of the following option leads to the portability and security of Java?", "a)Bytecode is executed by JVM ", "b)The applet makes the Java code secure and portable", "c)Use of exception handling", "d)Dynamic binding between objects", "a)Bytecode is executed by the JVM.");
        this.addQuestion(q1);
        Question q2=new Question("2. Which of the following is not a Java features?","a)Dynamic","b)Architecture Neutral","c)Use of pointers","d)Object-oriented","c)Use of pointers");
        this.addQuestion(q2);
        Question q3=new Question("3.The \u0021 article referred to as a","a)Unicode escape sequence","b)Octal escape","c)Hexadecimal","d)Line feed","a)Unicode escape sequence" );
        this.addQuestion(q3);
        Question q4=new Question("4. _____ is used to find and fix bugs in the Java programs.","a)JVM","b)JRE","c)JDK","d)JDB","d)JDB");
        this.addQuestion(q4);
        Question q5=new Question("5. What is the return type of the hashCode() method in the Object class?","a)object","b)int","c)long","d)void","b)int");
        this.addQuestion(q5);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        values.put(KEY_OPTD, quest.getOPTD());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
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
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}