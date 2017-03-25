package uqac.gestionvieetu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.OverScroller;

import java.util.Date;

public class MySQLiteHelper extends SQLiteOpenHelper {

    //Declaration de constantes pour les nom des tables et champs
    private static final int DATA_BASE_VERSION = 1;
    private static final String DATABASE_NAME = "BudgetBD";

    private static final String BUDGET_TABLE_NAME = "Budget";
    private static final String DR_TABLE_NAME = "DepenseRecette";
    private static final String DE_TABLE_NAME = "DetteEmprunt";

    private static final String KEY_ID = "id";
    private static final String KEY_MONTANT = "montant";
    private static final String KEY_SOLDE = "solde";
    private static final String KEY_MOIS = "mois";
    private static final String KEY_ANNEE = "annee";
    private static final String KEY_DATE = "date";
    private static final String KEY_DEPENSE = "depense";
    private static final String KEY_TYPE = "type";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_DETTE = "dette";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_BUDGET_TABLE = "CREATE TABLE" + BUDGET_TABLE_NAME + "(" +
                KEY_ID + "INT PRIMARY KEY AUTOINCREMENT," +
                KEY_MONTANT + "REAL," +
                KEY_SOLDE + "REAL," +
                KEY_MOIS + "DATE," +
                KEY_ANNEE + "annee DATE)";
        db.execSQL(CREATE_BUDGET_TABLE);

        String CREATE_DEPENSERECETTE_TABLE = "CREATE TABLE" + DR_TABLE_NAME + "(" +
                KEY_ID + "INT PRIMARY KEY AUTOINCREMENT," +
                KEY_MONTANT + "REAL," +
                KEY_TYPE + "TEXT," +
                KEY_DATE + "DATE," +
                KEY_DEPENSE + "BOOLEAN)";
        db.execSQL(CREATE_DEPENSERECETTE_TABLE);

        String CREATE_DETTEEMPRUNT_TABLE = "CREATE TABLE" + DE_TABLE_NAME + "(" +
                KEY_ID + "INT PRIMARY KEY AUTOINCREMENT," +
                KEY_MONTANT + "REAL," +
                KEY_TYPE + "TEXT," +
                KEY_DATE + "DATE," +
                KEY_NOM + "TEXT," +
                KEY_PRENOM + "TEXT," +
                KEY_DETTE + "BOOLEAN)";

        db.execSQL(CREATE_BUDGET_TABLE);
        db.execSQL(CREATE_DEPENSERECETTE_TABLE);
        db.execSQL(CREATE_DETTEEMPRUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST" + DE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST" + DR_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST" + BUDGET_TABLE_NAME);
        this.onCreate(db);
    }

    public void addBudget(TableBudget budget){
        Log.d("addBudget", budget.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MONTANT, budget.getMontant());
        values.put(KEY_SOLDE, budget.getSolde());
        values.put(KEY_MOIS, String.valueOf(budget.getMois()));
        values.put(KEY_ANNEE, String.valueOf(budget.getAnnee()));

        db.insert(BUDGET_TABLE_NAME, null, values);
        db.close();
    }

    public void addDepenseRecette(TableDepenseRecette depenseRecette){
        Log.d("addDepenseRecette", depenseRecette.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MONTANT, depenseRecette.getMontant());
        values.put(KEY_TYPE, depenseRecette.getType());
        values.put(KEY_DATE, String.valueOf(depenseRecette.getDate()));
        values.put(KEY_DEPENSE, depenseRecette.getDepense());

        db.insert(DR_TABLE_NAME, null, values);
        db.close();
    }

    public void addDetteEmprunt(TableDetteEmprunt detteEmprunt){
        Log.d("addDetteEmprunt", detteEmprunt.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MONTANT, detteEmprunt.getMontant());
        values.put(KEY_TYPE, detteEmprunt.getType());
        values.put(KEY_DATE, String.valueOf(detteEmprunt.getDate()));
        values.put(KEY_NOM, detteEmprunt.getNom());
        values.put(KEY_PRENOM, detteEmprunt.getPrenom());
        values.put(KEY_DEPENSE, detteEmprunt.getDette());

        db.insert(DE_TABLE_NAME, null, values);
        db.close();
    }
}
