package com.informramiz.github.kotlincoroutines.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.informramiz.github.kotlincoroutines.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        button_print.setOnClickListener {
            viewModel.printAfterDelay()
        }

        button_show_full_name.setOnClickListener {
            progress_circular.visibility = View.VISIBLE
            viewModel.fetchFullName().observe(viewLifecycleOwner, Observer { fullName ->
                progress_circular.visibility = View.GONE
                require(fullName != null)
                Toast.makeText(requireContext(), fullName, Toast.LENGTH_SHORT).show()
            })
        }
    }

}
