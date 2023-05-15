package com.alerdoci.ultimatepokedex.presentation.screens.pokemondetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.databinding.ActivityPokemonDetailBinding
import com.alerdoci.ultimatepokedex.presentation.screens.pokemondetail.viewmodel.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {
    private val viewModel: PokemonDetailViewModel by viewModels()
    private var binding: ActivityPokemonDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityPokemonDetailBinding.inflate(this.layoutInflater)
        setContentView(this.binding!!.root)
        val pokemonId = this.intent.getLongExtra("PokemonId", 0)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PokemonDetailFragment.newInstance(pokemonId))
                .commitNow()
        }
    }
}
