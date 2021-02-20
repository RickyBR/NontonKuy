package com.example.nontonkuy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{

    Context context;
    ArrayList<HistoryTicket> historyTicket;

    public  HistoryAdapter(Context c,ArrayList<HistoryTicket> p){
        context = c;
        historyTicket = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_myticket,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.xjumlah_tiket.setText(historyTicket.get(i).getJumlah_tiket()+ " Tickets");
        myViewHolder.xjudul_film.setText(historyTicket.get(i).getNama_film());
        myViewHolder.xno_cinema.setText(historyTicket.get(i).getCinema());

        String  getJudulFilm = historyTicket.get(i).getNama_film();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHistoryDetails = new Intent(context, HistoryDetailActivity.class);
                gotoHistoryDetails.putExtra("judulfilm", getJudulFilm);
                context.startActivity(gotoHistoryDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyTicket.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView xjudul_film,xjumlah_tiket,xno_cinema;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xjudul_film = itemView.findViewById(R.id.xjudul_film);
            xjumlah_tiket = itemView.findViewById(R.id.xjumlah_ticket);
            xno_cinema = itemView.findViewById(R.id.no_cinema);
        }
    }
}
