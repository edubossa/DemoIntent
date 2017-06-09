package com.ews.demointent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NovoUsuarioActivity extends AppCompatActivity {

    public static final String USERNAME = "com.ews.demointent.USERNAME";

    @BindView(R.id.etNewUsername) EditText etNewUsername;
    @BindView(R.id.etNewNome) EditText etNewNome;
    @BindView(R.id.etNewSenha) EditText etNewSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btCriar)
    public void criar(View view) {
        Intent intent = new Intent(); //TODO nao passa parametros nesse caso, pq a intencao vai ser um resultado
        intent.putExtra(USERNAME, this.etNewNome.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}