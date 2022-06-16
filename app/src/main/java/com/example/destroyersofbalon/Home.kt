package com.example.destroyersofbalon

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.destroyersofbalon.databinding.FragmentHomeBinding
import java.io.File


/**
 *
 *
 * Táto trieda,  symbolizuje prvú obrazovku
 * ktorá slúži na na orientovanie
 * Sú 2 možnosti, buď  hrač ide hrať hru alebo si ide do marketu
 * @property Home .
 * @constructor Zdedí  z triedy Fragment
 */
class Home : Fragment() {
    private val filename = "data.txt"
   private lateinit var binding: FragmentHomeBinding

    /**
     *  Vytvára obrazovku, nastavujú sa listeneri pre  buttony a načíta sa scóre
     * @return Vracia obrazovku.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        binding.playButton.setOnClickListener { view: View
            ->
            view.findNavController().navigate(R.id.action_home_to_game)
        }
        binding.shopButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_home_to_shop2)
        }
        binding.textView2?.setText(read().toString())

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.textView2?.setText(read().toString())

    }

    /**
     * Funkcia, ktorá vracia najvyššie score.
     * @return najväčšie score.
     */
    fun read() : Int {
        return context?.getSharedPreferences("data", 0)?.getInt("Score", 0)!!
    }

}

