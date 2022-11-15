package com.derekbearded.platformacme.shipments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.derekbearded.platformacme.data.ShipmentsProvider
import com.derekbearded.platformacme.data.ShipmentsSourceLocal
import com.derekbearded.platformacme.model.ShippingAssignment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShipmentsViewModel(application: Application) : AndroidViewModel(application) {

    private val _state: MutableStateFlow<ShipmentsState> =
        MutableStateFlow(ShipmentsState.Initial)
    val state: SharedFlow<ShipmentsState> = _state.asStateFlow()

    init {
        // Dependency Inversion / Injection happening here for simplicity. Normally would be handled
        // via library or more scalable framework
        val provider = ShipmentsSourceLocal(this.getApplication<Application>().baseContext)
        val client = ShipmentsProvider(provider)
        val service = ShipmentsService(client)

        viewModelScope.launch {
            val assignments = service.fetchAssignments()
            _state.update {
                ShipmentsState.Assignments(assignments = assignments)
            }
        }
    }
}

sealed class ShipmentsState {
    object Initial : ShipmentsState()
    data class Assignments(val assignments: List<ShippingAssignment>) : ShipmentsState()
}