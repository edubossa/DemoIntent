package com.ews.demointent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_NOVO_USUARIO = 1;

    @BindView(R.id.etUsername)
    EditText etUsername;

    @BindView(R.id.etSenha)
    EditText etSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        requestSmsPermission();
    }

    //Poderia chamar tbm no onCreate para restaurar a preservacao do estado
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String temp = savedInstanceState.getString("TEXTO") + " --> EWS";
            Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
            this.etUsername.setText(temp);
        }
        super.onRestoreInstanceState(savedInstanceState); //TODO testar
    }

    //Preservacao do estado
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("TEXTO", this.etUsername.getText().toString());
    }

    private void requestSmsPermission() { //TODO pedir permissao apenas no lugar onde a funcionalidade for requerida
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

    @OnClick(R.id.tvNovoUsuario)
    public void novoUsuario() { //TODO parametros do metodo e opcional
        Intent novoUsuario = new Intent(this, NovoUsuarioActivity.class);
        //Chama a intencao e retorna um resultado apos a tela chamada
        startActivityForResult(novoUsuario, REQUEST_CODE_NOVO_USUARIO);
    }

    @OnClick(R.id.btConnectar)
    public void conectar() {
        //Toast.makeText(this, "Funcionalidade nao implementada", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, SMSActivity.class);
        startActivity(intent);
    }

    //Obtem o retorno da tela chamada na intencao
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_CODE_NOVO_USUARIO:
                this.etUsername.setText(data.getStringExtra(NovoUsuarioActivity.USERNAME));
                break;
        }
    }

}
