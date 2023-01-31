package ru.andrewkir.vktestcase.flows.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.andrewkir.domain.repositories.MainRepository
import ru.andrewkir.vktestcase.App
import ru.andrewkir.vktestcase.R
import ru.andrewkir.vktestcase.common.BaseFragment
import ru.andrewkir.vktestcase.common.ViewModelFactory
import ru.andrewkir.vktestcase.databinding.FragmentMainBinding
import ru.andrewkir.vktestcase.flows.main.adapters.TodoAdapter
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var adapter: TodoAdapter

    override fun provideViewModel(): MainViewModel {
        (requireContext().applicationContext as App).appComponent.inject(this)
        return ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun provideBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TodoAdapter(emptyList(), { viewModel.removeItem(it) }, {
            viewModel.updateItem(it)
        })
        bind.recyclerView.adapter = adapter
        bind.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        bind.fab.setOnClickListener {
            showDialog()
        }

        subscribeToList()
    }

    private fun subscribeToList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.todoData.collectLatest {
                    adapter.setData(it)
                }
            }
        }
    }

    private fun showDialog() {
        val dialog = BottomSheetDialog(
            requireContext(),
            R.style.TransparentBottomSheetDialogTheme
        )
        dialog.setContentView(R.layout.bottom_sheet_layout)
        dialog.setCancelable(true)
        val bottomSheet = (requireView().parent as View)
        bottomSheet.backgroundTintMode = PorterDuff.Mode.CLEAR
        bottomSheet.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)

        val addButton = dialog.findViewById<Button>(R.id.addButton)
        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        val editText = dialog.findViewById<EditText>(R.id.editText)

        addButton?.setOnClickListener {
            val text = editText?.text.toString()
            if (text.isBlank()) {
                Toast.makeText(requireContext(), "Текст не может быть пустым", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.addItem(text)
                adapter.notifyItemInserted(viewModel.mTodoData.size)
                dialog.dismiss()
            }
        }
        cancelButton?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}