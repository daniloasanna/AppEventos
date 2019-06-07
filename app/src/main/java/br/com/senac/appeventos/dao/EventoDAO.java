package br.com.senac.appeventos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.senac.appeventos.modelo.Evento;

public class EventoDAO extends SQLiteOpenHelper {
    public EventoDAO(Context context) {
        super(context, "dbevento",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create Table tbevento(id integer Primary Key,nome text,local text,hora text,data text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbevento";
        db.execSQL(sql);
    }
    public void inserirEvento(Evento evento) {
        SQLiteDatabase inserir = getWritableDatabase();

        ContentValues dados = gtdados(evento);

        inserir.insert("tbevento",null, dados);
    }
    public List<Evento> listEvento(){
        String sql = "Select * from tbevento;";
        SQLiteDatabase db = getReadableDatabase();

        List<Evento> eventos = new ArrayList<Evento>();
        Cursor c = db.rawQuery(sql, null);


        while(c.moveToNext()) {
            Evento evento = new Evento();
            evento.setId(c.getLong(c.getColumnIndex("id")));
            evento.setNome(c.getString(c.getColumnIndex("nome")));
            evento.setLocal(c.getString(c.getColumnIndex("local")));
            evento.setHora(c.getString(c.getColumnIndex("hora")));
            evento.setData(c.getString(c.getColumnIndex("data")));



            eventos.add(evento);
        }
        c.close();
        return eventos;
    }
    public void deleta (Evento evento){
        SQLiteDatabase db = getWritableDatabase();
        String [] params = {evento.getId().toString()};
        db.delete("tbevento","id = ?",params);

    }
    public void altera (Evento evento){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = gtdados(evento);
        String [] params = {evento.getId().toString()};
        db.update("tbevento",dados,"id = ?",params);
    }

    private ContentValues gtdados(Evento evento) {
        ContentValues dados = new ContentValues();

        dados.put("nome", evento.getNome());
        dados.put("local",evento.getLocal());
        dados.put("hora",evento.getHora());
        dados.put("data",evento.getData());



        return dados;
    }
}
