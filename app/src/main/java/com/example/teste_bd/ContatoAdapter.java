package com.example.teste_bd;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ContatoAdapter extends ArrayAdapter<Contato> {
    private List<Contato> contatos;
    private Context context;

    public ContatoAdapter(Context context, List<Contato> contatos) {
        super(context, 0, contatos);
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lista_item_contato, parent, false);
        }

        Contato contato = getItem(position);

        TextView textViewNome = convertView.findViewById(R.id.textViewNome);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        TextView textViewTelefone = convertView.findViewById(R.id.textViewTelefone);

        textViewNome.setText(contato.getNome());
        textViewEmail.setText(contato.getEmail());
        textViewTelefone.setText(contato.getTelefone());

        return convertView;
    }
    public void atualizarContatos(List<Contato> novosContatos) {
        this.clear();
        this.addAll(novosContatos);
        notifyDataSetChanged();
    }
}
