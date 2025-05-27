package com.dam2.miprimeraapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dam2.miprimeraapp.R
import com.dam2.miprimeraapp.data.UserRepository
import com.dam2.miprimeraapp.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInfoBinding
    private val repository = UserRepository.repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showInfo()

        binding.btVolver.setOnClickListener {
            finish()
        }
    }

    /*
    Obtenemos el username a traves de Intent.
    Obtenemos el usuario completo y seteamos todos los textos
    con la información del usuario.
     */
    private fun showInfo() {
        val username = intent.getStringExtra("USERNAME")
        /*
        Debería llamar a .let, ya que el Intent devuelve un String?,
        pero prefiero el not nullable debido a la lógica de mi código,
        puesto que el Intent solo se va a realizar si los datos son correctos.
         */
        val user = repository.getUser(username!!)

        binding.tvUsername.text = user.username
        binding.tvInputfullname.text = user.fullname
        binding.tvInputedad.text = user.edad.toString()
        binding.tvInputtelf.text = user.telefono
    }
}