package uqac.gestionvieetu.Budget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fiolyne on 23/04/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int DATA_BASE_VERSION = 1;

    //Declaration des constantes pour les nom des tables et champs
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

    private static final String CREATE_BUDGET_TABLE =
             "CREATE TABLE" + BUDGET_TABLE_NAME + "(" +
             KEY_ID + "INT PRIMARY KEY AUTOINCREMENT," +
             KEY_MONTANT + "REAL," +
             KEY_SOLDE + "REAL," +
             KEY_MOIS + "DATE," +
             KEY_ANNEE + "annee DATE);";

    private static final String CREATE_DEPENSERECETTE_TABLE =
            "CREATE TABLE" + DR_TABLE_NAME + "(" +
            KEY_ID + "INT PRIMARY KEY AUTOINCREMENT," +
            KEY_MONTANT + "REAL," +
            KEY_TYPE + "TEXT," +
            KEY_DATE + "DATE," +
            KEY_DEPENSE + "BOOLEAN);";

    private static final String CREATE_DETTEEMPRUNT_TABLE =
            "CREATE TABLE" + DE_TABLE_NAME + "(" +
            KEY_ID + "INT PRIMARY KEY AUTOINCREMENT," +
            KEY_MONTANT + "REAL," +
            KEY_TYPE + "TEXT," +
            KEY_DATE + "DATE," +
            KEY_NOM + "TEXT," +
            KEY_PRENOM + "TEXT," +
            KEY_DETTE + "BOOLEAN)";

    public static final String DROP_BUDGET_TABLE = "DROP TABLE IF EXISTS " + BUDGET_TABLE_NAME + ";";
    public static final String DROP_DEPENSERECETTE_TABLE = "DROP TABLE IF EXISTS " + DR_TABLE_NAME + ";";
    public static final String DROP_DETTEEMPRUNT_TABLE = "DROP TABLE IF EXISTS " + DE_TABLE_NAME + ";";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATA_BASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BUDGET_TABLE);
        db.execSQL(CREATE_DEPENSERECETTE_TABLE);
        db.execSQL(CREATE_DETTEEMPRUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_BUDGET_TABLE);
        db.execSQL(DROP_DEPENSERECETTE_TABLE);
        db.execSQL(DROP_DETTEEMPRUNT_TABLE);
        onCreate(db);
    }
}
