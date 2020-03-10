package com.nexus.mvvmmessenger.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nexus.mvvmmessenger.R
import com.nexus.mvvmmessenger.constant.MessageDirection
import com.nexus.mvvmmessenger.databinding.ListItemIncomingMessageBinding
import com.nexus.mvvmmessenger.databinding.ListItemOutgoingMessageBinding
import com.nexus.mvvmmessenger.databinding.ListSectionItemMessageBinding
import com.nexus.mvvmmessenger.model.MessageModel
import com.nexus.mvvmmessenger.model.SectionMessageModel
import com.nexus.mvvmmessenger.utils.DateUtils
import java.util.ArrayList

class MessageAdapter(
    private val onCallback: OnCallback
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var messages = listOf<MessageModel>()
        set(value) {

            val tempList: ArrayList<MessageModel> = ArrayList()
            value
                .groupBy {
                    DateUtils.formatDate(
                        DateUtils.fullDateFormat,
                        DateUtils.readableDateFormat,
                        it.timestamp
                    )
                }
                .mapKeys { (_, values: List<MessageModel>) ->
                    // add section header 1st
                    tempList.add(SectionMessageModel(values[0].timestamp!!))
                    // append with grouped list
                    tempList.addAll(values)
                }
            field = tempList.sortedBy { it.timestamp }
            notifyDataSetChanged()
        }

    interface OnCallback {
        fun onLongClick(messageModel: MessageModel)
    }

    private val TYPE_INCOMING = 0
    private val TYPE_OUTGOING = 1
    private val TYPE_HEADER = 99

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> SectionViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_section_item_message,
                    parent,
                    false
                )
            )
            TYPE_INCOMING -> IncomingViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_incoming_message,
                    parent,
                    false
                ),
                this.onCallback
            )
            else -> OutgoingViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_outgoing_message,
                    parent,
                    false
                ),
                this.onCallback
            )
        }
    }


    override fun getItemViewType(position: Int): Int {
        val item = messages[position]
        return if (item is SectionMessageModel) {
            TYPE_HEADER
        } else {
            if (item.direction!! == MessageDirection.INCOMING.value) {
                TYPE_INCOMING
            } else {
                TYPE_OUTGOING
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = messages[position]
        if (item is SectionMessageModel && holder is SectionViewHolder) {
            holder.bind(item)
        } else {
            if (holder is IncomingViewHolder) {
                holder.bind(item)
            } else if (holder is OutgoingViewHolder) {
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }


    class IncomingViewHolder(
        private val binding: ListItemIncomingMessageBinding,
        private val onCallback: OnCallback
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(messageModel: MessageModel) {
            with(binding) {
                viewModel = MessageItemViewModel(messageModel)
            }
            binding.layout.setOnLongClickListener {
                onCallback.onLongClick(binding.viewModel!!.messageModel)
                false
            }

        }

    }

    class OutgoingViewHolder(
        private val binding: ListItemOutgoingMessageBinding,
        private val onCallback: OnCallback
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(messageModel: MessageModel) {
            with(binding) {
                viewModel = MessageItemViewModel(messageModel)
            }
            binding.layout.setOnLongClickListener {
                onCallback.onLongClick(binding.viewModel!!.messageModel)
                true
            }

        }

    }


    class SectionViewHolder(
        private val binding: ListSectionItemMessageBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sectionMessageModel: SectionMessageModel) {
            with(binding) {
                viewModel = MessageSectionViewModel(sectionMessageModel)
            }

        }

    }

}

