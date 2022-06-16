package com.example.destroyersofbalon

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Rect

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.destroyersofbalon.databinding.FragmentGameBinding
import android.os.Handler;
import android.os.Looper
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.navigation.findNavController

import kotlin.collections.ArrayList
import kotlin.math.log
import kotlin.random.Random

/**
 * Trieda slúžiaca na priebeh hry.
 */
class Game : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var xDelta = 0
    private var yDelta = 0
    private var z =0
    private var k =0
    private var speed = 100
    private var score =0
    private lateinit var binding : FragmentGameBinding
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private var array : ArrayList<View> = ArrayList<View>()
    private var arrayOb : ArrayList<View> = ArrayList<View>()
    private var arrayBalonov : ArrayList<View> = ArrayList<View>()
    private val home:Home = Home()
    private var random: Random = Random


    /**
     * Funkcia ktorá slúži na načítavanie  animacie pre balon,určovanie typ balona,
     */
    @android.annotation.SuppressLint("ClickableViewAccessibility", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater,
                R.layout.fragment_game,
                container,
                false
            )
             binding.gameBaseBalon.setOnTouchListener(object: View.OnTouchListener
            {
                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            xDelta = (view.x - event.rawX).toInt()
                            yDelta =(view.y - event.rawY).toInt()
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val screenX = event.rawX + xDelta
                            val screenY = event.rawY + yDelta
                            if(screenX > 0 && screenY > 0 ) {
                                view.animate()
                                    .x(event.rawX + xDelta)
                                    .y(event.rawY + yDelta)
                                    .setDuration(0)
                                    .start()
                            }
                        }

                        else -> return false
                    }
                    return true
                }
            })
        array.add(binding.destroyer1)
        array.add(binding.destroyer2)
        array.add(binding.destroyer3)
        array.add(binding.destroyer4)
        array.add(binding.destroyer5)
        array.add(binding.destroyer6)
        array.add(binding.destroyer7)
        array.add(binding.destroyer8)
       when(context?.getSharedPreferences("data", 0)?.getInt("Balon", 0)!!)
       {
           1 -> {
               binding.gameBaseBalon.setImageResource(R.drawable.pinkbalon)
           }
           2 -> {
               binding.gameBaseBalon.setImageResource(R.drawable.yellowbalon)

           }
           3 ->
           {
               binding.gameBaseBalon.setImageResource(R.drawable.starbalon)

           }
           4 ->
           {
               binding.gameBaseBalon.setImageResource(R.drawable.spiralebalon)

           }
       }
            return binding.root
    }

    /**
     * Nastavenie zakladných vecí pre hru
     */
    override fun onStart() {
        super.onStart()


        score = 0
       for (i in 0..7) {
           var nieco = random.nextInt(
               0,
               Resources.getSystem().displayMetrics.widthPixels - 200
           )
           array.get(i).visibility = View.VISIBLE
           array.get(i).animate().x(nieco.toFloat()).y((-300).toFloat()).setDuration(0).start()
           if(!arrayOb.isEmpty())
           {
               arrayOb.clear()
           }
           arrayOb.add(array.get(0))
       }
        k= 1

        startTimer()

    }

    override fun onResume() {
        super.onResume()
        startTimer()
    }
    /**
     * Nekonečná slučka, ktorá končí keď sa 2 objekty narazia
     */
    fun startTimer() {
        runnable = Runnable {

            handler.postDelayed(runnable, speed.toLong())
            for (i in 0..k-1) {
                arrayOb.get(i).animate().y(arrayOb.get(i).y.plus(10)).x(arrayOb.get(i).x).setDuration(0)
                    .start()
                if (arrayOb.get(i).y > ( binding.game.height).toFloat()) {
                    var nieco = random.nextInt(
                        0,
                        Resources.getSystem().displayMetrics.widthPixels - arrayOb.get(i).width
                    )
                    Log.i("s",i.toString())

                    arrayOb.get(i).animate().x(nieco.toFloat()).y((-300).toFloat()).setDuration(0).start()
                        arrayOb.removeAt(i)
                    score+=5
                    if (speed != 1)
                    speed-=1
                    k-=1
                    break



                }
            }


            if (z == 50 ) {
                arrayOb.add(array.get(k))
                k += 1
                z = 0
                Log.i("v",k.toString())

            }
            for (i in 0..k - 1) {
                var balon = Rect(
                    binding.gameBaseBalon.x.toInt(),
                    binding.gameBaseBalon.y.toInt(),
                    (binding.gameBaseBalon.x + binding.gameBaseBalon.width).toInt(),
                    (((binding.gameBaseBalon.y + binding.gameBaseBalon.height))).toInt()
                )
                var nieco = Rect(
                    arrayOb.get(i).x.toInt(),
                    arrayOb.get(i).y.toInt(),
                    (arrayOb.get(i).x + arrayOb.get(i).width).toInt(),
                    (arrayOb.get(i).y + arrayOb.get(i).height).toInt()
                )
                if (balon.intersect(nieco)) {
                    handler.removeCallbacks(runnable)
                    if (score > read() )
                    {
                        save(score)
                    }
                    binding.game.findNavController().navigate(R.id.action_game_to_gameOver2)
                    break;
                }

            }
        z++
            binding.score?.setText(score.toString())

        }




        // This is what initially starts the timer
        handler.postDelayed(runnable, 1000)

    }
    fun save(cislo:Int)
    {

        val editor =  context?.getSharedPreferences("data", 0)?.edit()
        editor?.putInt("Score", cislo)
        editor?.apply()
    }

    fun read() : Int {
        return context?.getSharedPreferences("data", 0)?.getInt("Score", 0)!!

    }
}