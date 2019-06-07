package br.com.senac.appeventos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.senac.appeventos.dao.EventoDAO;
import br.com.senac.appeventos.modelo.Evento;

public class FormularioActivity extends AppCompatActivity {
    private FormActivity helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormActivity(this);

        Intent intent = getIntent();
        Evento evento = (Evento) intent.getSerializableExtra("evento");

        if(evento != null){
            helper.alterform(evento);
            Toast.makeText(FormularioActivity.this, "Está tudo certo",Toast.LENGTH_LONG).show();
        }

        Button botaosalvar = (Button) findViewById(R.id.btsave);
        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Evento evento = helper.getEvento();
                EventoDAO dao = new EventoDAO(FormularioActivity.this);
                if (evento.getId() != null){
                    dao.altera(evento);
                    Toast.makeText(FormularioActivity.this,evento.getNome() + "foi alterado.",Toast.LENGTH_LONG).show();
                }else{
                    dao.inserirEvento(evento);
                    Toast.makeText(FormularioActivity.this, "Evento " + evento.getNome() + " salvo.", Toast.LENGTH_SHORT).show();
                }

                dao.close();


            }
        });

        Button botaolista = (Button) findViewById(R.id.btlist);
        botaolista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linklista = new Intent(FormularioActivity.this, ListActivity.class);
                startActivity(linklista);
            }
        });
    }
}
