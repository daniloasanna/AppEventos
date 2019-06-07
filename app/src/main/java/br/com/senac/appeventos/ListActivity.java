package br.com.senac.appeventos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

import br.com.senac.appeventos.dao.EventoDAO;
import br.com.senac.appeventos.modelo.Evento;

public class ListActivity extends AppCompatActivity {
    private ListView listevento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        EventoDAO dao = new EventoDAO(this);
        final List<Evento> eventos = dao.listEvento();

        listevento = (ListView) findViewById(R.id.listevento);

        listevento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento evento = (Evento) listevento.getItemAtPosition(position);
                Intent linkalterar = new Intent(ListActivity.this,FormularioActivity.class);
                linkalterar.putExtra("evento",evento);

                startActivity(linkalterar);
            }
        });
        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this, android.R.layout.simple_list_item_activated_1, eventos);
        listevento.setAdapter(adapter);

        Button btback = findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent linkback = new Intent(ListActivity.this,FormularioActivity.class);
                startActivity(linkback);
            }
        });
        registerForContextMenu(listevento);

    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                Evento evento = (Evento) listevento.getItemAtPosition(info.position);
                Toast.makeText(ListActivity.this,evento.getNome() + " " + "foi deletado ", Toast.LENGTH_LONG).show();

                EventoDAO dao = new EventoDAO(ListActivity.this);
                dao.deleta(evento);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregaLista(){
        EventoDAO dao = new EventoDAO(this);
        List<Evento> eventos = dao.listEvento();
        dao.close();

        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1, eventos);
        listevento.setAdapter(adapter);
    }
}
