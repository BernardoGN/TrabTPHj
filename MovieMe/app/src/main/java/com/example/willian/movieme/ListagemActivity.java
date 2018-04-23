package com.example.willian.movieme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListagemActivity extends Activity {
    private static List<Listagem> listagens = new ArrayList<>(); // entrada de dados (implementar banco de dados)
    private static ListagemAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        final ListView listaDeFilmes = (ListView) findViewById(R.id.lista);
        final ListagemAdapter adapter = new ListagemAdapter(listagens, this);
        mAdapter = adapter;
        listaDeFilmes.setAdapter(adapter);
        // Código para remover filme com swipe //
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        listaDeFilmes,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }
                            // Se for para a direita deleta //
                            @Override
                            public void onDismissRight(ListView listView, int[] reverseSortedPositions) {
                                for (final int position : reverseSortedPositions) {
                                    //Criar Dialog quando o usuário usar o swipe
                                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ListagemActivity.this);

                                    alertBuilder.setIcon(R.drawable.ic_launcher_foreground);
                                    alertBuilder.setTitle(getString(R.string.dialogTitle));
                                    alertBuilder.setMessage(getString(R.string.dialogMessage));

                                    alertBuilder.setPositiveButton(getString(R.string.dialogPositive), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            listagens.remove(position);
                                            adapter.notifyDataSetChanged();
                                            Toast.makeText(getBaseContext(), getString(R.string.toastFilmeRemovido), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    alertBuilder.setNegativeButton(getString(R.string.dialogNegative), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                                    AlertDialog dialog = alertBuilder.create();
                                    dialog.show();

                                }
                            }
                            // Se for para a esquerda compartilha //
                            @Override
                            public void onDismissLeft(ListView listView, int[] reverseSortedPositions){
                                for (final int position : reverseSortedPositions) {
                                    // Background de Compartilhar //
                                    //listaDeFilmes.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                                    // Intent para compartilhar //
                                    Listagem filme = listagens.get(position);
                                    Intent intent = new Intent(Intent.ACTION_SEND);
                                    intent.setType("text/*");
                                    intent.putExtra(Intent.EXTRA_SUBJECT, filme.getNome());
                                    intent.putExtra(Intent.EXTRA_TEXT, filme.getAno() + "\n" + filme.getDiretor() + "\n" + filme.getGenero());
                                    startActivity(Intent.createChooser(intent, "Share via"));
                                }
                            }
                        });
        listaDeFilmes.setOnTouchListener(touchListener);

        // Quando pressionar o botão  de adicionar //

        Button btnAdd = findViewById(R.id.listagem_btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListagemActivity.this, AddFilmeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpa activity
                startActivity(intent);
            }
        });

    }

    public List<Listagem> EntradaTeste() {

        List<Listagem> listagens = new ArrayList<>();

        Listagem filme1 = new Listagem("Jurassic Park", "Ficção", "Steven Spielberg", 10, "1993");

        Listagem filme2 = new Listagem("Munique", "Drama", "Steven Spielberg", 16, "2006");

        Listagem filme3 = new Listagem("O Terminal", "Comédia, Drama", "Steven Spielberg", 12, "2004");

        Listagem filme4 = new Listagem("Jurassic Park", "Ficção", "Steven Spielberg", 10, "1993");

        Listagem filme5 = new Listagem("Munique", "Drama", "Steven Spielberg", 16, "2006");

        Listagem filme6 = new Listagem("O Terminal", "Comédia, Drama", "Steven Spielberg", 12, "2004");

        listagens.add(filme1);
        listagens.add(filme2);
        listagens.add(filme3);
        listagens.add(filme4);
        listagens.add(filme5);
        listagens.add(filme6);

        return listagens;
    }

    public static void AddFilme(Listagem l) {
        listagens.add(l);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Share Provider //
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider shareProvider = (ShareActionProvider) shareItem.getActionProvider();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, "Texto para Compartilhar");

        // Exibe a intent //
        shareProvider.setShareIntent(intent);
        return true;
    }

    public static void AddViewDel(){
        mAdapter.del();
    }

    public static void AddViewShare(){
        mAdapter.share();
    }

}
