package com.sonukgupta72.cricketgame.target

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.sonukgupta72.cricketgame.Constants
import com.sonukgupta72.cricketgame.PlayCricketActivity
import com.sonukgupta72.cricketgame.R


class TargetFragment : Fragment() {

    companion object {
        fun newInstance() = TargetFragment()
    }

    lateinit var tvShowTargetScore: TextView

    private lateinit var viewModel: TargetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.target_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TargetViewModel::class.java)

        tvShowTargetScore = view?.findViewById<TextView>(R.id.tv_show_target_score)!!

        viewModel.createRandomNumber()
        viewModel.getRandomScoreLiveData().observe(this, Observer {this.showTarget(it) })


    }

    fun showTarget(score: Int) {
        tvShowTargetScore.text = "Your target score is " + score + " run!"
        (activity as PlayCricketActivity).setTarget(score);
        Handler().postDelayed({ launchNextFragment() }, 2000)

    }

    private fun launchNextFragment() {
        (activity as PlayCricketActivity).changeFragment(Constants.FRAGMENT_PLAY_GAME)
    }

}
