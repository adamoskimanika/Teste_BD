package com.example.teste_bd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextEmail, editTextTelefone;
    private Button btnAdicionar, btnVerContatos;
    private ListView listViewContatos;
    private DatabaseHelper dbHelper;
    private ContatoAdapter contatoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inicializar os elementos da interface do usuário
        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listViewContatos = findViewById(R.id.listViewContatos);
              // Inicializar o DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        // Configurar o adaptador para a lista de contatos
        List<Contato> contatos = dbHelper.obterTodosContatos();
        contatoAdapter = new ContatoAdapter(this, contatos);
        listViewContatos.setAdapter(contatoAdapter);
        // Lidar com o clique no botão "Adicionar Contato"
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os dados do formulário
                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                String telefone = editTextTelefone.getText().toString();
                // Inserir o novo contato no banco de dados
                dbHelper.inserirContato(new Contato(nome, email, telefone));
                // Atualizar a lista de contatos
                contatoAdapter.atualizarContatos(dbHelper.obterTodosContatos());
                // Limpar os campos do formulário
                editTextNome.setText("");
                editTextEmail.setText("");
                editTextTelefone.setText("");
            }
        });
        // Lidar com o clique em um item da lista para exclusão
        listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contatoSelecionado = contatos.get(position);
                dbHelper.excluirContato(contatoSelecionado);
                // Atualizar a lista de contatos após a exclusão
                contatoAdapter.atualizarContatos(dbHelper.obterTodosContatos());
            }
        });
        btnVerContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaContatosActivity.class);
                startActivity(intent);
            }
        });
    }
}