package com.sakhura.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.sakhura.todolist.adapter.TareasAdapter
import com.sakhura.todolist.databinding.ActivityMainBinding
import com.sakhura.todolist.model.Tarea
import com.sakhura.todolist.utils.SwipeToDeleteCallback


class MainActivity : AppCompatActivity() {

    //inicializador adapter
    private lateinit var binding: ActivityMainBinding
    private val listaTareas = mutableListOf<Tarea>()
    private lateinit var adapter: TareasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  val listaTareas = mutableMapOf<Tarea>()

        adapter = TareasAdapter(listaTareas) { actualizarContador() }

        binding.rvTareas.layoutManager = LinearLayoutManager(this)
        binding.rvTareas.adapter = adapter

        ItemTouchHelper(SwipeToDeleteCallback {

            adapter.eliminarTarea(it)
        }).attachToRecyclerView(binding.rvTareas)

        binding.btnAgregarTarea.setOnClickListener {
            val titulo = binding.etNuevaTarea.text.toString()
            if (titulo.isNotBlank()) {
                adapter.agregarTarea(Tarea(titulo))
                binding.etNuevaTarea.text.clear()
            } else {
                Toast.makeText(this, "Ingrese una tarea", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnEliminarTareas.setOnClickListener {
            listaTareas.removeAll { it.completada }
            adapter.notifyDataSetChanged()
            actualizarContador()
        }
    }



        private fun actualizarContador(){
            val pendientes = adapter.obtenerTareas().count() { !it.completada }
            binding.tvContador.text = "Tareas Pendientes: $pendientes"

        }


}


