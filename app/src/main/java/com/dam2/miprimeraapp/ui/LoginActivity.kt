package com.dam2.miprimeraapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dam2.miprimeraapp.R
import com.dam2.miprimeraapp.data.UserRepository
import com.dam2.miprimeraapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    /*
    View Binding, una funcionalidad de Android Jetpack, que genera una
    clase de enlace (binding) por cada XML de diseño que tengamos
    en nuestro módulo.

    Dicha clase, contiene referencias tipadas y null-safe a todas las
    Views que tengan un android:id dentro del XML, eliminando la necesidad
    de findViewById() y los casteos manuales.
     */
    private lateinit var binding: ActivityLoginBinding
    /*
    Singleton que contiene información de los usuarios.
     */
    private val repository = UserRepository.repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        Construimos la UI y obtenemos una instancia de esa clase de ayuda
        con referencias tipadas.
         */
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        /*
        Mostramos la raíz de esa UI.
         */
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
        Al pulsar sobre el boton login, se ejecutará lo que haya dentro de la función.
         */
        binding.btLogin.setOnClickListener {
            loginPressed()
        }
    }

    private fun loginPressed() {
        /*
        Extraemos los textos del edit text
         */
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        /*
        Validamos las credenciales.
        En caso positivo, mandamos el username introducido a la otra activity,
        para poder obtener el usuario con toda su información.
        En caso negativo, se nos mostrará un mensaje de error.
         */
        if (repository.validateUsers(username, password)) {
            val intent = Intent(this, UserInfoActivity::class.java).apply {
                putExtra("USERNAME", username)
            }

            startActivity(intent)
        } else {
            Toast.makeText(this, "User name o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}