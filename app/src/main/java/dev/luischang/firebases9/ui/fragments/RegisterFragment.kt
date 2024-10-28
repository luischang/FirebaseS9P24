package dev.luischang.firebases9.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.luischang.firebases9.R
import dev.luischang.firebases9.database.CustomerEntity

class RegisterFragment : Fragment() {
    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View = inflater.inflate(R.layout.fragment_register, container, false)

        val etFirstName: EditText = view.findViewById(R.id.etFirstName)
        val etLastName: EditText = view.findViewById(R.id.etLastName)
        val etPhoneNumber: EditText = view.findViewById(R.id.etPhoneNumber)
        val btnSaveCustomer: Button = view.findViewById(R.id.btnSaveCustomer)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        btnSaveCustomer.setOnClickListener {
            val customerEntity =
                CustomerEntity(etFirstName.text.toString()
                    , etLastName.text.toString()
                    , etPhoneNumber.text.toString())
            viewModel.saveCustomer(customerEntity)
            addObserver()
        }

        return view
    }

    private fun addObserver(){
        val observer  =  Observer<List<CustomerEntity>>{customers->
            var text = "";
            for (customer in customers) {
                text += "${customer.firstName} ${customer.lastName}"
                Log.i("ROOMDATABASE", "Customer--> " + text)
            }
        }
        viewModel.customers.observe(viewLifecycleOwner, observer)
    }
}