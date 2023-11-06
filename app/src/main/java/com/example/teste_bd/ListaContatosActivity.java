package com.example.teste_bd;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;

public class ListaContatosActivity extends Activity {

    private ListView listViewContatos;
    private ContatoAdapter contatoAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);
        Button button = findViewById(R.id.button);

        listViewContatos = findViewById(R.id.listViewContatos);
        dbHelper = new DatabaseHelper(this);

        // Obter a lista de contatos do banco de dados
        List<Contato> contatos = dbHelper.obterTodosContatos();

        // Configurar o adaptador para exibir os contatos
        contatoAdapter = new ContatoAdapter(this, contatos);
        listViewContatos.setAdapter(contatoAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
