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
import com.semenovnikolay.clothingstore.presentation.viewModel.AddClothesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddClothes : Fragment() {

    private var binding: FragmentAddClothesBinding? = null
    private var addAdapter : AddAdapter? = null
/*    private var list : List<String> = listOf<String>("кот", "петжак", "кровать")
    private var listText : List<String> = listOf<String>("лес", "книга", "стол")*/

    private val addClothesViewModel: AddClothesViewModel by viewModel()

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
        loadClothes()


        return binding?.root
    /*inflater.inflate(R.layout.fragment_add_clothes, container, false)*/
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun setUpAdapter() {
        binding?.catalogClothes?.layoutManager = LinearLayoutManager(context)
        addAdapter = AddAdapter(this.getActivity()!!/*,list*//*, listText*/)

        binding?.catalogClothes?.adapter = addAdapter

    }

    private fun initRecyclerAddClothes(){
        binding?.catalogClothes?.layoutManager =
            LinearLayoutManager(context)
        addAdapter = AddAdapter(this.requireActivity())
        binding?.catalogClothes?.adapter = addAdapter

    }

    private fun loadClothes() {

        // получение всех необходимых данных для заполнения recyclerView
        addClothesViewModel.loadAddClothes.observe(viewLifecycleOwner, Observer {
            // setList наполняет адаптер данными
            addAdapter?.setList(it)
            // notifyDataSetChanged обновляет адаптер
            addAdapter?.notifyDataSetChanged()
        })
    }

}