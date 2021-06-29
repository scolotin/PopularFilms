package com.scolotin.popularfilms.presentation.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scolotin.popularfilms.R
import com.scolotin.popularfilms.databinding.FragmentFilmsBinding
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.presentation.abs.AbsFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class FilmsFragment : AbsFragment(R.layout.fragment_films), FilmsView {

    companion object {

        fun newInstance() = FilmsFragment()

    }

    @Inject
    lateinit var filmsPresenterFactory: FilmsPresenterFactory

    private val presenter: FilmsPresenter by moxyPresenter {
        filmsPresenterFactory.create()
    }

    private var adapter = FilmsAdapter()

    private var vb: FragmentFilmsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        FragmentFilmsBinding
            .inflate(inflater, container, false)
            .apply {
                vb = this
            }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vb?.preview?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()

        vb = null
    }

    override fun showFilms(films: List<Film>) {
        adapter.run {
            submitList(films)
            notifyDataSetChanged()
        }
    }

}
