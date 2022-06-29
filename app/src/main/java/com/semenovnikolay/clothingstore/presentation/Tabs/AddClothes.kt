package com.semenovnikolay.clothingstore.presentation.Tabs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenovnikolay.clothingstore.R
import com.semenovnikolay.clothingstore.databinding.FragmentAddClothesBinding
import androidx.lifecycle.Observer

class AddClothes : Fragment() {

    private var binding: FragmentAddClothesBinding? = null
    private var addAdapter : AddAdapter? = null
    private var list : List<String> = listOf<String>("кот", "петжак", "кровать")
    private var listText : List<String> = listOf<String>("лес", "книга", "стол")

/*    override fun onCreate(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_clothes, container, false)

        return binding?.root
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_clothes, container, false)

      //  initRecyclerAddClothes()
        setUpAdapter()

        return binding?.root
    /*inflater.inflate(R.layout.fragment_add_clothes, container, false)*/
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun setUpAdapter() {

        addAdapter = AddAdapter(this.getActivity()!!,list/*, listText*/)

        binding?.catalogClothes?.adapter = addAdapter
        binding?.catalogClothes?.layoutManager = LinearLayoutManager(context)
    }

    private fun initRecyclerAddClothes(){
        binding?.catalogClothes?.layoutManager =
            LinearLayoutManager(context)
       // addAdapter = AddAdapter(list)
        binding?.catalogClothes?.adapter = addAdapter

    }

    private fun loadClothes() {



/*       addAdapter?*/

/*        // получение всех необходимых данных для заполнения recyclerView
        medicationsViewModel.loadMedicines.observe(viewLifecycleOwner, Observer {
            // setList наполняет адаптер данными
            medicationsAdapter?.setList(it)
            // notifyDataSetChanged обновляет адаптер
            medicationsAdapter?.notifyDataSetChanged()
        })*/
    }

}