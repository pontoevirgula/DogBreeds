package com.chsltutorials.dogbreeds.presentation.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chsltutorials.dogbreeds.R
import com.chsltutorials.dogbreeds.core.bases.BaseFragment
import com.chsltutorials.dogbreeds.core.util.Empty
import com.chsltutorials.dogbreeds.core.util.Failure
import com.chsltutorials.dogbreeds.core.util.Loading
import com.chsltutorials.dogbreeds.core.util.Success
import com.chsltutorials.dogbreeds.model.entity.Breed
import com.chsltutorials.dogbreeds.presentation.IBreedContract
import com.chsltutorials.dogbreeds.presentation.presenter.BreedPresenter
import com.chsltutorials.dogbreeds.presentation.view.adapter.BreedAdapter
import com.chsltutorials.dogbreeds.presentation.view.adapter.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_breed.*
import retrofit2.HttpException

class BreedFragment : BaseFragment<IBreedContract.BreedPresenter>(), IBreedContract.BreedView {

    override fun showEmptySuccess(resource: Empty<List<String>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var adapterRv: BreedAdapter

    companion object {
        @JvmField
        var TAG: String = BreedFragment::class.java.simpleName
    }

    private fun populateBreed(items: List<String>) : List<Breed>{

        val breeds: MutableList<Breed> = ArrayList()

        for (name : String in items){
            val breed = Breed(name)
            breed.nome = name
            breeds.add(breed)
        }

        return breeds
    }


    override fun showSuccess(resource: Success<List<String>>) {
        pbLoadingBreed.visibility = View.GONE
        toolbarBreed.visibility = View.VISIBLE
        val breedList = populateBreed(resource.data)
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

    override fun showError(resource: Failure) {
        val error = resource.error as HttpException
        Log.e("ERRO",error.message())
        pbLoadingBreed.visibility = View.GONE
        rvBreeds.visibility = View.GONE
        toolbarBreed.visibility = View.GONE
    }

    override fun showLoading(loading: Loading) {
        pbLoadingBreed.visibility = View.VISIBLE
        rvBreeds.visibility = View.GONE
        toolbarBreed.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_breed, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fetchBreedList()
    }

    override fun createPresenter(): IBreedContract.BreedPresenter {
        return BreedPresenter(this)
    }

}