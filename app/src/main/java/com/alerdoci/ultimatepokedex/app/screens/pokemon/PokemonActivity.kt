package com.alerdoci.ultimatepokedex.app.screens.pokemon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.databinding.ActivityPokemonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {
    private var binding: ActivityPokemonBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        this.binding = ActivityPokemonBinding.inflate(this.layoutInflater)
        setContentView(this.binding!!.root)

        val pokemonName = this.intent.getStringExtra("pokemonName")!!

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PokemonFragment.newInstance(pokemonName))
                .commitNow()
        }
    }
}
