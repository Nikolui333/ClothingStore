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
import com.semenovnikolay.clothingstore.data.models.AddLocalModel
import com.semenovnikolay.clothingstore.presentation.viewModel.AddClothesViewModel
import com.semenovnikolay.clothingstore.presentation.viewModel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddClothes : Fragment() {

    private var binding: FragmentAddClothesBinding? = null
    private var addAdapter : AddAdapter? = null

    private val addClothesViewModel: AddClothesViewModel by viewModel()
    private val cardViewModel: CardViewModel by viewModel()

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

  //  @SuppressLint("UseRequireInsteadOfGet")
    private fun setUpAdapter() {
        binding?.catalogClothes?.layoutManager = LinearLayoutManager(context)
        addAdapter = AddAdapter({ addLocalModel: AddLocalModel ->
            addToCard(
                addLocalModel
            )
        },
            { addLocalModel: AddLocalModel ->
            lessSize(
                addLocalModel
            )
        }, { addLocalModel: AddLocalModel ->
            moreSize(
                addLocalModel
            )
        })

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

    // уменьшение колличества единиц товара
    private fun lessSize(addLocalModel:AddLocalModel) {

        var size: Int = addLocalModel.size.toInt()
        size--
        if (size<1) { // если size<40 вывести 40
            addClothesViewModel.updateClothesSize(
                AddLocalModel(addLocalModel.id, addLocalModel.name,
                    addLocalModel.image, addLocalModel.description, addLocalModel.discount, addLocalModel.price,  /*addLocalModel.idProduct,*/ "1"
                    /*(addLocalModel.price.toInt()*40).toString()*/)
            )

        }
        else {

            addClothesViewModel.updateClothesSize(
                AddLocalModel(addLocalModel.id, addLocalModel.name,
                    addLocalModel.image, addLocalModel.description, addLocalModel.discount, addLocalModel.price, /* addLocalModel.idProduct,*/ size.toString()
                    /*(addLocalModel.price.toInt()*size).toString()*/)
            )

        }
    }

    // добавление товара в корзину
    private fun addToCard(addLocalModel: AddLocalModel) {
        cardViewModel.startInsert(addLocalModel.name,
            addLocalModel.image,
            addLocalModel.price,
            addLocalModel.id.toString(),
            /*"1",*/
            addLocalModel.size
        )
    }

    // увеличение колличества единиц товара
    private fun moreSize(addLocalModel:AddLocalModel) {

/*        // получаем колличество товара
        var count: Int = addLocalModel.size.toInt()
        count++*/

/*        addClothesViewModel.updateClothesSize(
            AddLocalModel(addLocalModel.id, addLocalModel.name,
                addLocalModel.image, addLocalModel.price, addLocalModel.description, addLocalModel.idProduct, count.toString(),
                (addLocalModel.price.toInt()*count).toString())
        )*/

        // получаем колличество товара
        var size: Int = addLocalModel.size.toInt()
        size++

        addClothesViewModel.updateClothesSize(
            AddLocalModel(addLocalModel.id, addLocalModel.name,
                addLocalModel.image, addLocalModel.description, addLocalModel.discount, addLocalModel.price, /*addLocalModel.idProduct,*/ size.toString()
        ))
    }

}