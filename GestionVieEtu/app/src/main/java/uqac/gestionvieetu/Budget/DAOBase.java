package uqac.gestionvieetu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Fiolyne on 23/04/2017.
 */

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
    public long ajouterBudget(TableBudget bg){
        ContentValues value = new ContentValues();
        value.put(KEY_MONTANT, bg.getMontant());
        value.put(KEY_SOLDE, bg.getSolde());
        value.put(KEY_MOIS, String.valueOf(bg.getMois()));
        value.put(KEY_ANNEE, String.valueOf(bg.getAnnee()));
        return sqlDb.insert(BUDGET_TABLE_NAME, null, value);
    }
}

