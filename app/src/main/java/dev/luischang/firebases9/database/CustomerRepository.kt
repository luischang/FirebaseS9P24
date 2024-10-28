package dev.luischang.firebases9.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerRepository(application: Application) {

    private val customerDao: CustomerDao? =
        CustomerDatabase.getInstance(application)?.customerDao()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    // Función para insertar utilizando Coroutines
    fun insert(customerEntity: CustomerEntity) {
        customerDao?.let { dao ->
            coroutineScope.launch {
                dao.insert(customerEntity)
            }
        }
    }

    // Función para obtener la lista de clientes
    fun getCustomers(): LiveData<List<CustomerEntity>> {
        return customerDao?.getCustomerOrderByLastName() ?: MutableLiveData<List<CustomerEntity>>().apply {
            value = emptyList()
        }
    }
}