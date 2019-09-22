package com.sonukgupta72.cricketgame.playAgain

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonukgupta72.cricketgame.Constants
import com.sonukgupta72.cricketgame.PlayCricketActivity
import com.sonukgupta72.cricketgame.R
import kotlinx.android.synthetic.main.play_again_fragment.*


class PlayAgainFragment : Fragment() {

    companion object {
        fun newInstance() = PlayAgainFragment()
    }

    private lateinit var viewModel: PlayAgainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.play_again_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayAgainViewModel::class.java)
        btn_play_again.setOnClickListener(View.OnClickListener {
            (activity as PlayCricketActivity).changeFragment(Constants.FRAGMENT_TARGET_SCORE)
        })
    }

}
