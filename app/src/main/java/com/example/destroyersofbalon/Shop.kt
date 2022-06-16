package com.example.destroyersofbalon

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.example.destroyersofbalon.databinding.FragmentGameOverBinding
import com.example.destroyersofbalon.databinding.FragmentShopBinding


/**
 * Trieda ktorá slúži pre market.
 * Obsahuje nastavovanie čo sa má vykonať pre jednotlivé buttony  pod balónmi.
 *
 */
class Shop : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    /**
     * Funkcia uloží, ktorý balon si uživateľ vybral a následne nastaví popisky pre buttony
     */
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentShopBinding>(
            inflater,
            R.layout.fragment_shop,
            container,
            false
        )
        binding.pinkbutton.setOnClickListener {
            binding.pinkbutton.setText("Selected")
            binding.yellowbutton.setText("Select")
            binding.starbutton.setText("Select")


            save(1)
        }
        binding.yellowbutton.setOnClickListener {
            binding.yellowbutton.setText("Selected")
            binding.pinkbutton.setText("Select")
            binding.starbutton.setText("Select")


            save(2)
        }
        binding.starbutton.setOnClickListener {
            binding.starbutton.setText("Selected")
            binding.pinkbutton.setText("Select")
            binding.yellowbutton.setText("Select")


            save(3)
        }
        binding.spiralbuton.setOnClickListener {
            binding.spiralbuton.setText("Selected")
            binding.yellowbutton.setText("Select")
            binding.starbutton.setText("Select")
            binding.pinkbutton.setText("Select")
            save(4)
        }
        return binding.root
    }
    /**
     * Ukladá vyber
     */

    fun save(cislo:Int)
    {

        val editor =  context?.getSharedPreferences("data", 0)?.edit()
        editor?.putInt("Balon", cislo)
        editor?.apply()
    }
}