package com.scolotin.popularfilms.presentation.films

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.scolotin.popularfilms.R
import com.scolotin.popularfilms.databinding.FragmentFilmsBinding
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.presentation.abs.AbsFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class FilmsFragment : AbsFragment(R.layout.fragment_films), FilmsView, FilmsAdapter.Delegate {

    companion object {

        fun newInstance(): Fragment = FilmsFragment()

    }

    @Inject
    lateinit var filmsPresenterFactory: FilmsPresenterFactory

    private val presenter: FilmsPresenter by moxyPresenter {
        filmsPresenterFactory.create()
    }

    private var adapter = FilmsAdapter(this)

    private val vb: FragmentFilmsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vb.preview.adapter = adapter
    }

    override fun showFilms(films: List<Film>) {
        adapter.run {
            submitList(films)
            notifyDataSetChanged()
        }
    }

    override fun showError(message: String?) {
        message?.let {
            Snackbar.make(requireContext(), requireView(), it, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onPicked(film: Film) {
        presenter.displayFilm(film)
    }

}
