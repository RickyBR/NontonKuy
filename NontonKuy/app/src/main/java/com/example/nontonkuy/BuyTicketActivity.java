package com.example.nontonkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuyTicketActivity extends AppCompatActivity {
    Button btn_plus,btn_minus;
    TextView total_tickets,totalprice;
    Integer valuetotaltickets = 1;
    Integer valuetotalprice= 0;
    Integer valueticketprice = 35000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        total_tickets = findViewById(R.id.total_tickets);
        totalprice = findViewById(R.id.totalprice);


        total_tickets.setText(valuetotaltickets.toString());
        valuetotalprice = valueticketprice * valuetotaltickets;
        totalprice.setText(valuetotalprice.toString());

        btn_minus.animate().alpha(0).setDuration(300).start();
        btn_minus.setEnabled(false);


        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuetotaltickets+=1;
                total_tickets.setText(valuetotaltickets.toString());

                if(valuetotaltickets > 1){
                    btn_minus.animate().alpha(1).setDuration(300).start();
                    btn_minus.setEnabled(true);
                }
                valuetotalprice = valueticketprice * valuetotaltickets;
                totalprice.setText(valuetotalprice.toString());
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuetotaltickets-=1;
                total_tickets.setText(valuetotaltickets.toString());
                if(valuetotaltickets < 2){
                    btn_minus.animate().alpha(0).setDuration(300).start();
                    btn_minus.setEnabled(false);
                }
                valuetotalprice = valueticketprice * valuetotaltickets;
                totalprice.setText(valuetotalprice.toString());

            }
        });



    }
}