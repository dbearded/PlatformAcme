package com.derekbearded.platformacme

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.derekbearded.platformacme.shipments.ShipmentsAdapter
import com.derekbearded.platformacme.shipments.ShipmentsState
import com.derekbearded.platformacme.shipments.ShipmentsViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: ShipmentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shipmentsAdapter = ShipmentsAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.matched_shipments_list).apply {
            adapter = shipmentsAdapter
        }
        val progressBar = findViewById<CircularProgressIndicator>(R.id.shipments_progress)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is ShipmentsState.Initial -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.INVISIBLE
                        }
                        is ShipmentsState.Assignments -> {
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                            shipmentsAdapter.submitList(it.assignments)
                        }
                    }
                }
            }
        }
    }
}