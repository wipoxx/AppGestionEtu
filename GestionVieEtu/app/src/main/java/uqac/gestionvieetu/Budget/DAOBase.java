package uqac.gestionvieetu.Budget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

//**************** Classe non utilisée **************************//
public class DAOBase {
    private static final int DATA_BASE_VERSION = 1;
    private static final String DATABASE_NAME = "BudgetBD";

    protected SQLiteDatabase sqlDb;
    protected DataBase db;

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

    private static final String[] allColumnsB = {KEY_ID, KEY_MONTANT, KEY_SOLDE, KEY_MOIS, KEY_ANNEE};
    private static final String[] allColumnsDR = {KEY_ID, KEY_MONTANT, KEY_TYPE, KEY_DATE, KEY_DEPENSE};

    public DAOBase(Context context) {
        this.db = new DataBase(context);
    }

    public void open() {
        sqlDb = db.getWritableDatabase();
    }

    public void close() {
        sqlDb.close();
    }

    public SQLiteDatabase getDb() {
        return sqlDb;
    }


    //BUDGET
    public TableBudget ajouterBudget(TableBudget bg){
        ContentValues value = new ContentValues();
        value.put(KEY_MONTANT, bg.getMontant());
        value.put(KEY_SOLDE, bg.getSolde());
        value.put(KEY_MOIS, String.valueOf(bg.getMois()));
        value.put(KEY_ANNEE, String.valueOf(bg.getAnnee()));
        long insert = sqlDb.insert(BUDGET_TABLE_NAME, null, value);

        Cursor cursor = sqlDb.query(BUDGET_TABLE_NAME, allColumnsB, KEY_ID + " = " + insert, null, null, null, null);
        cursor.moveToFirst();
        TableBudget newBudget = cursorToBudget(cursor);
        cursor.close();
        return newBudget;
    }

    public List<TableBudget> getAllBuget() {
        List<TableBudget> budget = new ArrayList<TableBudget>();

        Cursor cursor = sqlDb.query(BUDGET_TABLE_NAME, allColumnsB, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableBudget bg = cursorToBudget(cursor);
            budget.add(bg);
            cursor.moveToNext();
        }
        cursor.close();
        return budget;
    }

    private TableBudget cursorToBudget(Cursor cursor) {
        TableBudget budget = new TableBudget();
        budget.setId(cursor.getInt(0));
        budget.setMontant(cursor.getFloat(1));
        budget.setSolde(cursor.getFloat(2));
        budget.setMois(cursor.getString(3));
        budget.setAnnee(cursor.getString(4));

        return budget;
    }

    public TableDepenseRecette ajouterDepense(TableDepenseRecette dr){
        ContentValues value = new ContentValues();
        value.put(KEY_MONTANT, dr.getMontant());
        value.put(KEY_TYPE, dr.getType());
        value.put(KEY_DATE, dr.getDate());
        value.put(KEY_DEPENSE, dr.getDepense());
        long insert = sqlDb.insert(DR_TABLE_NAME, null, value);

        Cursor cursor = sqlDb.query(DR_TABLE_NAME, allColumnsDR, KEY_ID + " = " + insert, null, null, null, null);
        cursor.moveToFirst();
        TableDepenseRecette newDR = cursorToDR(cursor);
        cursor.close();
        return newDR;
    }

    public List<TableDepenseRecette> getAllDR() {
        List<TableDepenseRecette> depRec = new ArrayList<TableDepenseRecette>();

        Cursor cursor = sqlDb.query(DR_TABLE_NAME, allColumnsDR, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TableDepenseRecette dr = cursorToDR(cursor);
            depRec.add(dr);
            cursor.moveToNext();
        }
        cursor.close();
        return depRec;
    }

    private TableDepenseRecette cursorToDR(Cursor cursor) {
        TableDepenseRecette depRec = new TableDepenseRecette();
        depRec.setId(cursor.getInt(0));
        depRec.setMontant(cursor.getFloat(1));
        depRec.setType(cursor.getString(2));
        depRec.setDate(cursor.getString(3));
        depRec.setDepense(cursor.getInt(4));

        return depRec;
    }


}

