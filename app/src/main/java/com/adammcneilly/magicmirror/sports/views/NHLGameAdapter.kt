package com.adammcneilly.magicmirror.sports.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.magicmirror.databinding.NhlGameViewBinding
import com.adammcneilly.magicmirror.sports.models.NHLGame

class NHLGameAdapter : RecyclerView.Adapter<NHLGameAdapter.NHLGameViewHolder>() {

    var games: List<NHLGame>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NHLGameViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = NhlGameViewBinding.inflate(inflater, parent, false)
        return NHLGameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return games?.size ?: 0
    }

    override fun onBindViewHolder(holder: NHLGameViewHolder, position: Int) {
        holder.bindGame(games?.get(position))
    }

    class NHLGameViewHolder(binding: NhlGameViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = NHLGameViewModel()

        init {
            binding.viewModel = viewModel
        }

        fun bindGame(game: NHLGame?) {
            viewModel.game = game
        }
    }
}