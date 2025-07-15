package com.sakhura.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakhura.todolist.databinding.ItemTareaBinding
import com.sakhura.todolist.model.Tarea

class TareasAdapter(
    private val tareas: MutableList<Tarea>,
    private val onCheckChange: () -> Unit
): RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(val : binding: ItemTareaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val binding = ItemTareaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return TareaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.binding.tvTituloTarea.text = tarea.titulo
        holder.binding.cbCompletada.isChecked = tarea.completada

        holder.binding.cbCompletada.setOnClickListener {
            tarea.completada = holder.binding.cbCompletada.isChecked
            onCheckChange()
        }

    }
     override fun getItemCount(): tarea.size

    fun eliminarTarea(pos: Int){
        tareas.removeAt(pos)
        notifyItemRemoved(pos)
        onCheckChange()
    }

    fun agregarTarea(tarea: Tarea){
        tareas.add(tarea)
        notifyItemInserted(0)
        onCheckChange()
    }

    fun obtenerTareas() : List<Tarea> = tareas

}