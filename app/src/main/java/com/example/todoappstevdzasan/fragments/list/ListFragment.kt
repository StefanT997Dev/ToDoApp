package com.example.todoappstevdzasan.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappstevdzasan.R
import com.example.todoappstevdzasan.data.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    private val mToDoViewModel:ToDoViewModel by viewModels()

    private val adapter:ListAdapter by lazy{ListAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView=view.recyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireActivity())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {data->
            adapter.setData(data)
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_gragment_menu,menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Set Menu
        setHasOptionsMenu(true)
    }
}