package com.example.willian.movieme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BtnDelAdapter extends BaseAdapter {
    // lista que conterá a linguagens a serem exibidas
    private ArrayList<Buttons> buttons;
    private Context context;

    public BtnDelAdapter(Context context) {
        this.context = context;
        buttons = new ArrayList<>();

        this.buttons.add(new Buttons());
    }
    @Override
    public int getCount() {
        return this.buttons.size();
    }
    @Override
    public Object getItem(int i) {
        return this.buttons.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Buttons button = this.buttons.get(i);
        View newView = LayoutInflater.from(this.context).inflate(R.layout.btn_del, viewGroup, false);


        return newView;
    }

}
