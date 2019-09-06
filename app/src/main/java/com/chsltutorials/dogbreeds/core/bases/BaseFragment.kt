package com.chsltutorials.dogbreeds.core.bases

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chsltutorials.dogbreeds.model.entity.Breed


abstract class BaseFragment<P : IBaseContract.Presenter> : Fragment(), IBaseContract.View<P> {

    lateinit var presenter : P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetach()
    }

    fun moveToAnotherFragment(id: Int, fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(id, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun getBreedClicked(nome : Breed) : String {
      return nome.toString()
    }


}