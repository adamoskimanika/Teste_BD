package com.example.teste_bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MinhaBaseDeDados.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Contatos (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT, Email TEXT, Telefone TEXT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Contatos");
        onCreate(db);
    }

    public void inserirContato(Contato contato) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nome", contato.getNome());
        values.put("Email", contato.getEmail());
        values.put("Telefone", contato.getTelefone());
        db.insert("Contatos", null, values);
        db.close();
    }

    public List<Contato> obterTodosContatos() {
        List<Contato> listaContatos = new ArrayList<>();
        String selectQuery = "SELECT * FROM Contatos";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contato contato = new Contato();
                contato.setID(cursor.getInt(0));
                contato.setNome(cursor.getString(1));
                contato.setEmail(cursor.getString(2));
                contato.setTelefone(cursor.getString(3));
                listaContatos.add(contato);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaContatos;
    }

    public void atualizarContato(Contato contato) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nome", contato.getNome());
        values.put("Email", contato.getEmail());
        values.put("Telefone", contato.getTelefone());
        db.update("Contatos", values, "ID = ?", new String[]{String.valueOf(contato.getID())});
        db.close();
    }

    public void excluirContato(Contato contato) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Contatos", "ID = ?", new String[]{String.valueOf(contato.getID())});
        db.close();
    }
}


