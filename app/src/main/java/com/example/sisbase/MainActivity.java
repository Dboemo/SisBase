package com.example.sisbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText unome, usenha;
    Button bentrar;
    BancoControle banco = new BancoControle(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unome = (EditText) findViewById(R.id.editTextUsr);
        usenha = (EditText) findViewById(R.id.editTextsenha);
        bentrar = (Button) findViewById(R.id.buttonlogar);
        final List<usuario> usr = banco.Consulta_todos_usuarios();
        try{
            if (usr.size() ==0) {
                usuario u = new usuario();
                u.setNome("Admin");
                u.setSenha("Admin");
                u.setEmail("admin@admin.com");
                banco.insere_usuario(u);
            }}catch(Exception ex){
            usuario u = new usuario();
            u.setNome("Admin");
            u.setSenha("Admin");
            u.setEmail("admin@admin.com");
            banco.insere_usuario(u);
        }
        bentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unome.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Entre com um usuario", Toast.LENGTH_LONG).show();
                } else if (usenha.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Entre com uma senha", Toast.LENGTH_LONG).show();
                } else {

                    final List<usuario> usr = banco.Consulta_todos_usuarios();

                    String usuario = unome.getText().toString().trim();
                    String senha = usenha.getText().toString().trim();


                    if (banco.checkusuario(usuario,senha)) {
                        Intent i = new Intent(getApplicationContext(), principalActivity.class);
                        startActivity(i);
                    } else {
                        usenha.setText("");
                        unome.setText("");
                        unome.requestFocus();
                        Toast.makeText(MainActivity.this, "Usuario incorreto", Toast.LENGTH_SHORT).show();


                    }
                }
            }
        });
    }
}