package com.derekbearded.platformacme.shipments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.derekbearded.platformacme.R
import com.derekbearded.platformacme.model.ShippingAssignment

class ShipmentsAdapter :
    ListAdapter<ShippingAssignment, ShipmentsAdapter.ShipmentsViewHolder>(ShipmentsDiff) {

    class ShipmentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val driverName = itemView.findViewById<TextView>(R.id.driver_name)
        private val address = itemView.findViewById<TextView>(R.id.shipping_address)

        fun bind(assignment: ShippingAssignment) {
            driverName.text = assignment.driver.name
            address.text = assignment.shipment.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.driver_shipment, parent, false)
        return ShipmentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShipmentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object ShipmentsDiff : DiffUtil.ItemCallback<ShippingAssignment>() {
    override fun areItemsTheSame(
        oldItem: ShippingAssignment,
        newItem: ShippingAssignment
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: ShippingAssignment,
        newItem: ShippingAssignment
    ): Boolean = oldItem == newItem
}