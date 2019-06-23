package com.example.appbank.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbank.R;
import com.example.appbank.data.remote.IStatementService;
import com.example.appbank.data.remote.ServiceGenerator;
import com.example.appbank.data.remote.model.Statement;
import com.example.appbank.data.remote.model.StatementResponse;
import com.example.appbank.ui.adapter.AdapterAccount;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {

    private ImageButton imageButtonLogout;
    private RecyclerView recyclerViewAccount;
    private List<AccountActivity> listAccount;
    private TextView textViewName;
    private TextView textViewConta;
    private TextView textViewBankAccount;
    private TextView textViewSaldo;
    private TextView textViewBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        loadUi();
        setFonts();

        Bundle b = getIntent().getExtras();

        if (b != null) {
            textViewName.setText(b.getString("Name"));
            textViewBankAccount.setText(b.getString("BankAccount") + " / " + b.getString("Agency"));

            Locale locale = new Locale("pt", "BR");
            NumberFormat format = NumberFormat.getCurrencyInstance(locale);
            String currency = format.format(b.getDouble("Balance"));
            textViewBalance.setText(currency);
            getStatements(b.getInt("Id"));
        }

        imageButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getStatements(int Id) {
        IStatementService statementService = ServiceGenerator.createService(IStatementService.class);
        Call<StatementResponse> call = statementService.getStatement(Id);

        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                if (response.isSuccessful()) {
                    StatementResponse statementResponse = response.body();
                    configAdapter(statementResponse.getStatementList());
                }
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao obter o seu extrato!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void configAdapter(List<Statement> statements) {
        AdapterAccount adapter = new AdapterAccount(AccountActivity.this, statements);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAccount.setLayoutManager(layoutManager);
        recyclerViewAccount.setHasFixedSize(true);
        recyclerViewAccount.setAdapter(adapter);

    }

    private void loadUi() {
        imageButtonLogout = findViewById(R.id.imageButtonLogout);
        recyclerViewAccount = findViewById(R.id.recyclerViewAccount);
        textViewName = findViewById(R.id.textViewName);
        textViewConta = findViewById(R.id.textViewConta);
        textViewBankAccount = findViewById(R.id.textViewBankAccount);
        textViewSaldo = findViewById(R.id.textViewSaldo);
        textViewBalance = findViewById(R.id.textViewBalance);

    }

    private void setFonts() {
        Typeface normal = Typeface.createFromAsset(getAssets(),
                "HelveticaNeueLight.ttf");

        textViewName.setTypeface(normal);
        textViewConta.setTypeface(normal);
        textViewBankAccount.setTypeface(normal);
        textViewSaldo.setTypeface(normal);
        textViewBalance.setTypeface(normal);
    }

}
