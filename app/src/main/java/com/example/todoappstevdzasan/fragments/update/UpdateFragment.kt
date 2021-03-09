package com.example.todoappstevdzasan.fragments.update

import android.os.Bundle
import android.renderscript.RenderScript
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoappstevdzasan.R
import com.example.todoappstevdzasan.data.models.Priority
import com.example.todoappstevdzasan.data.models.ToDoData
import com.example.todoappstevdzasan.data.viewmodel.ToDoViewModel
import com.example.todoappstevdzasan.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel:ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=return inflater.inflate(R.layout.fragment_update, container, false)

        // Set menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        current_title_et.setText(args.currentItem.title)
        current_description_et.setText(args.currentItem.description)
        current_priorities_spinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        current_priorities_spinner.onItemSelectedListener=mSharedViewModel.listener
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_save){
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val title=current_title_et.text.toString()
        val description=current_description_et.text.toString()
        val getPriority=current_priorities_spinner.selectedItem.toString()

        val validation=mSharedViewModel.verifyDataFromUser(title,description)
        if(validation) {
            val updatedItem= ToDoData(
                    args.currentItem.id,
                    title,
                    mSharedViewModel.parsePriority(getPriority),
                    description
            )
            mToDoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(),"Successfully updated",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_SHORT).show()
        }
    }
}