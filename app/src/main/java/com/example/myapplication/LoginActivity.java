package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText txtUser;
    private TextInputEditText txtPassword; // Cambiado de editTextPassword a txtPassword
    private Button buttonAccept; // Cambiado de buttonAccept a button2
    private Button buttonBack; // Renombrado de buttonGo a buttonBack (para el botón "Atras")
    private Button buttonCancel; // Cambiado de buttonCancel a button3

    // Usuario y clave válidos para este ejemplo (puedes cambiarlos)
    private static final String VALID_USERNAME = "Michelle";
    private static final String VALID_PASSWORD = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar los elementos de la UI con los IDs de tu XML
        txtUser = findViewById(R.id.txtuser);
        txtPassword = findViewById(R.id.txtpassword);
        buttonAccept = findViewById(R.id.button2); // ID del botón "Aceptar"
        buttonBack = findViewById(R.id.button4);     // ID del botón "Atras" (antes "Go")
        buttonCancel = findViewById(R.id.button3); // ID del botón "Cancelar"

        // Configurar OnClickListener para el botón "Aceptar"
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        // Configurar OnClickListener para el botón "Atras"
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // El botón "Atras" simplemente finaliza la actividad para retroceder.
                finish();
                Toast.makeText(LoginActivity.this, "Regresando...", Toast.LENGTH_SHORT).show();
            }
        });


        // Configurar OnClickListener para el botón "Cancelar"
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // El botón "Cancelar" ahora limpia los campos de texto
                txtUser.setText(""); // Limpiar el campo de usuario
                txtPassword.setText(""); // Limpiar el campo de contraseña
                txtUser.setError(null); // Borrar cualquier error previo del usuario
                txtPassword.setError(null); // Borrar cualquier error previo de la contraseña
                Toast.makeText(LoginActivity.this, "Campos limpiados.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void attemptLogin() {
        // Reiniciar errores
        txtUser.setError(null);
        txtPassword.setError(null);

        // Obtener el texto ingresado
        String username = txtUser.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        // Verificar si la clave está vacía
        if (TextUtils.isEmpty(password)) {
            txtPassword.setError("La clave es requerida."); // Mensaje de error
            focusView = txtPassword;
            cancel = true;
        }

        // Verificar si el usuario está vacío
        if (TextUtils.isEmpty(username)) {
            txtUser.setError("El usuario es requerido."); // Mensaje de error
            focusView = txtUser;
            cancel = true;
        }

        if (cancel) {
            // Hubo un error; no intentar iniciar sesión y enfocar el primer campo con error.
            if (focusView != null) {
                focusView.requestFocus();
            }
        } else {
            // En una aplicación real, aquí harías una llamada a una API o a un servicio de autenticación.
            // Para este ejemplo, solo verificaremos los valores codificados.
            if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
                Toast.makeText(LoginActivity.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_LONG).show();
                // TODO: Navegar a la siguiente actividad (ej. MainActivity)
                // Ejemplo:
                // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                // startActivity(intent);
                // finish(); // Cierra la actividad de login para que el usuario no pueda volver a ella con el botón de retroceso
            } else {
                Toast.makeText(LoginActivity.this, "Usuario o clave incorrectos.", Toast.LENGTH_LONG).show();
                txtUser.setError("Credenciales inválidas.");
                txtPassword.setError("Credenciales inválidas.");
                txtUser.requestFocus(); // Enfocar el campo de usuario si las credenciales son incorrectas
            }
        }
    }
}
