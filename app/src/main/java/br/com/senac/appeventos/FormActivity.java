package br.com.senac.appeventos;

import android.widget.EditText;

import br.com.senac.appeventos.modelo.Evento;

public class FormActivity  {
    private final EditText nome;
    private final EditText hora;
    private final EditText data;
    private final EditText local;
    private Evento evento;

    public FormActivity(FormularioActivity Activity) {

        nome = Activity.findViewById(R.id.nome);
        local = Activity.findViewById(R.id.local);
        hora = Activity.findViewById(R.id.hora);
        data = Activity.findViewById(R.id.data);



        evento = new Evento();

    }

    public Evento getEvento(){

        evento.setNome(nome.getText().toString());
        evento.setLocal(local.getText().toString());
        evento.setHora(hora.getText().toString());
        evento.setData(data.getText().toString());


        return evento;
    }

    public void alterform(Evento evento) {
        nome.setText(evento.getNome());
        local.setText(evento.getLocal());
        data.setText(evento.getData());
        hora.setText(evento.getHora());



        this.evento = evento;
    }
}
