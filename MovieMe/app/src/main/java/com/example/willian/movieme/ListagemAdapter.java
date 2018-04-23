package com.example.willian.movieme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Willian on 30/03/2018.
 */

public class ListagemAdapter extends BaseAdapter {

    private final List<Listagem> listagens;
    private final Activity activity;
    private static Integer margin_topDel = 100;
    private static Integer margin_topShare = 100;

    public ListagemAdapter(List<Listagem> listagens, Activity activity){
        this.listagens = listagens;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listagens.size();
    }

    @Override
    public Object getItem(int position) {
        return listagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.listagem_filmes, parent, false);

        Listagem listagem = listagens.get(position);

        ImageView imgFaixa= view.findViewById(R.id.img_faixa);
        TextView nome = view.findViewById(R.id.txt_nome);
        TextView diretor = view.findViewById(R.id.txt_diretor);
        TextView ano = view.findViewById(R.id.txt_ano);
        TextView genero = view.findViewById(R.id.txt_genero);

        nome.setText(listagem.getNome());
        diretor.setText(listagem.getDiretor());
        ano.setText(listagem.getAno());
        genero.setText(listagem.getGenero());
        imgFaixa.setImageResource(listagem.getFaixa());

        return view;
    }

    public void del(){
        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.drawable.ic_delete_black_24dp);
        imageView.setPadding(25, margin_topDel, 0, 0);
        margin_topDel+=200;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        activity.addContentView(imageView, layoutParams);
    }


    public void share(){
        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.drawable.ic_share_black_24dp);
        imageView.setPadding(1300, margin_topShare, 0, 0);
        margin_topShare+=200;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        activity.addContentView(imageView, layoutParams);
    }


}
