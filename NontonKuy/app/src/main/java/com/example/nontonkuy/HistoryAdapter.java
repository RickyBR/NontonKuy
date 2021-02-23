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
    ArrayList<HistoryTicket> historyTickets;

    public  HistoryAdapter(Context c,ArrayList<HistoryTicket> p){
        context = c;
        historyTickets = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_myticket,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.xjumlah_tiket.setText(historyTickets.get(i).getJumlah_tiket()+ " Tickets");
        myViewHolder.xjudul_film.setText(historyTickets.get(i).getNama_film());
        myViewHolder.xno_cinema.setText(historyTickets.get(i).getCinema());
        myViewHolder.xid_film.setText(historyTickets.get(i).getId_film());
        myViewHolder.xtotalprice.setText(historyTickets.get(i).getTotal_harga());
        myViewHolder.xtime_ticket.setText(historyTickets.get(i).getWaktu_film());

        String  getJudulFilm = historyTickets.get(i).getId_film();

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
        return historyTickets.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView xjudul_film,xjumlah_tiket,xno_cinema,xid_film,xtotalprice,xtime_ticket;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xjudul_film = itemView.findViewById(R.id.xjudul_film);
            xjumlah_tiket = itemView.findViewById(R.id.xjumlah_ticket);
            xno_cinema = itemView.findViewById(R.id.no_cinema);
            xid_film = itemView.findViewById(R.id.id_film);
            xtotalprice = itemView.findViewById(R.id.xtotalprice);
            xtime_ticket = itemView.findViewById(R.id.xtime_ticket);

        }
    }
}
