package com.example.destroyersofbalon

import android.app.Dialog
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.example.destroyersofbalon.databinding.FragmentGameOverBinding



/**
 * Trieda  ktorá určuje čo bude po prehratí hry.
 */
class GameOver : DialogFragment() {

    /**
     * Nastavovanie  listenerov pre buttony
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    val binding = DataBindingUtil.inflate<FragmentGameOverBinding>(
        inflater,
        R.layout.fragment_game_over,
        container,
        false
    )
        binding.reset.setOnClickListener { view: View
            ->
            view.findNavController().navigate(R.id.action_gameOver2_to_game)
        }
        binding.menu.setOnClickListener { view: View
            ->
            view.findNavController().navigate(R.id.action_gameOver2_to_home)
        }
        return binding.root

    }


}