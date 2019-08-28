package com.chsltutorials.dogbreeds.presentation.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chsltutorials.dogbreeds.R
import com.chsltutorials.dogbreeds.base.BaseFragment
import com.chsltutorials.dogbreeds.model.entity.Breed
import com.chsltutorials.dogbreeds.presentation.IBreedContract
import com.chsltutorials.dogbreeds.presentation.presenter.BreedPresenter
import com.chsltutorials.dogbreeds.presentation.view.adapter.BreedAdapter
import com.chsltutorials.dogbreeds.presentation.view.adapter.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_breed.*

class BreedFragment : BaseFragment<IBreedContract.BreedPresenter>(), IBreedContract.BreedView {

    lateinit var adapterRv: BreedAdapter

    companion object {
        @JvmField
        var TAG: String = BreedFragment::class.java.simpleName
    }

    private fun populateBreed(items: List<String>) : List<Breed>{

        val breeds: MutableList<Breed> = ArrayList()
        lateinit var breed : Breed

        for (name : String in items){
            breed = Breed(name)
            breed.nome = name
            breeds.add(breed)
        }

        return breeds
    }


    override fun showSuccess(names : List<String>) {
        pbLoadingBreed.visibility = View.GONE
        toolbarBreed.visibility = View.VISIBLE
        val breedList = populateBreed(names)
        adapterRv = BreedAdapter(breedList,object : OnItemClickListener{
            override fun onItemClick(item: Breed) {
                rvBreeds.visibility = View.GONE
                toolbarBreed.visibility = View.GONE
                val bundle = Bundle()
                bundle.putString("name",item.nome)
                val dogDetailFragment = DogDetailFragment()
                dogDetailFragment.arguments = bundle
                moveToAnotherFragment(R.id.contentFrameFragment,dogDetailFragment)
            }
        })
        with(rvBreeds) {
            visibility = View.VISIBLE
            adapter = adapterRv
            layoutManager = LinearLayoutManager(activity)
        }
        adapterRv.notifyDataSetChanged()
    }

    override fun showError() {
        pbLoadingBreed.visibility = View.GONE
        rvBreeds.visibility = View.GONE
        toolbarBreed.visibility = View.GONE
    }

    override fun showLoading() {
        pbLoadingBreed.visibility = View.VISIBLE
        rvBreeds.visibility = View.GONE
        toolbarBreed.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_breed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fetchBreedList()
    }

    override fun createPresenter(): IBreedContract.BreedPresenter {
        return BreedPresenter(this)
    }

}