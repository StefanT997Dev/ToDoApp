package com.example.todoappstevdzasan.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoappstevdzasan.R
import com.example.todoappstevdzasan.SharedViewModelFactory
import com.example.todoappstevdzasan.ToDoViewModelFactory
import com.example.todoappstevdzasan.data.ToDoDao
import com.example.todoappstevdzasan.data.models.Priority
import com.example.todoappstevdzasan.data.models.ToDoData
import com.example.todoappstevdzasan.data.viewmodel.ToDoViewModel
import com.example.todoappstevdzasan.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private val mToDoViewModel by lazy {
        ViewModelProvider(this,ToDoViewModelFactory(requireActivity().application)).get(ToDoViewModel::class.java)
    }

    private val mSharedViewModel by lazy {
        ViewModelProvider(this,SharedViewModelFactory(requireActivity().application)).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)

        // Set Menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        priorities_spinner.onItemSelectedListener=mSharedViewModel.listener
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_add){
            insertDataToDataDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDataDb() {
        val mTitle=title_et.text.toString()
        val mPriority=priorities_spinner.selectedItem.toString()
        val mDescription=description_et.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle,mDescription)

        if(validation){
            // Insert Data to Database
            val newData= ToDoData(
                    0,
                    mTitle,
                    mSharedViewModel.parsePriority(mPriority),
                    mDescription
            )

            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_SHORT).show()
        }
    }
}