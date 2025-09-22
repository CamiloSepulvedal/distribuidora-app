package com.example.distribuidoraapp2.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.distribuidoraapp2.R
import com.google.firebase.database.*

class MenuActivity : AppCompatActivity() {

    private var dialogMostrado = false
    private var tempListener: ValueEventListener? = null
    private lateinit var tempRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val etTotal = findViewById<EditText>(R.id.etTotalCompra)
        val etKm = findViewById<EditText>(R.id.etDistanciaKm)
        val btnCalc = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val tvDetalle = findViewById<TextView>(R.id.tvDetalle)
        val swMonitoreo = findViewById<Switch>(R.id.swMonitoreo)
        val tvTemp = findViewById<TextView>(R.id.tvTempActual)

        // Mostrar correo del usuario autenticado
        val userEmail = intent.getStringExtra("userEmail")
        Toast.makeText(this, "Bienvenido $userEmail", Toast.LENGTH_SHORT).show()

        // Calcular costo de despacho
        btnCalc.setOnClickListener {
            if (etTotal.text.isNullOrEmpty() || etKm.text.isNullOrEmpty()) {
                Toast.makeText(this, "Ingrese total y distancia", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val total = etTotal.text.toString().toLong()
            val km = etKm.text.toString().toDouble()

            val costo = calcularDespacho(total, km)
            tvResultado.text = "Costo de despacho: $${costo.toLong()}"
            tvDetalle.text = detalleRegla(total, km, costo)
        }

        // Monitoreo de temperatura desde Firebase
        tempRef = FirebaseDatabase.getInstance().getReference("truck/temperature")

        swMonitoreo.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                dialogMostrado = false
                tempListener = tempRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val temp = snapshot.getValue(Double::class.java) ?: return
                        tvTemp.text = "Temperatura actual: ${"%.1f".format(temp)} °C"

                        if (temp > 5.0 && !dialogMostrado) {
                            dialogMostrado = true
                            AlertDialog.Builder(this@MenuActivity)
                                .setTitle("⚠️ Alerta de temperatura")
                                .setMessage("La temperatura superó el límite permitido (> 5°C).")
                                .setPositiveButton("OK") { d, _ ->
                                    d.dismiss()
                                    dialogMostrado = false
                                }
                                .show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        tvTemp.text = "Error: ${error.message}"
                    }
                })
            } else {
                tempListener?.let { tempRef.removeEventListener(it) }
                tempListener = null
                tvTemp.text = "Monitoreo desactivado"
            }
        }
    }

    // Calcular costo según reglas de negocio
    private fun calcularDespacho(total: Long, km: Double): Double {
        return when {
            total >= 50_000 && km <= 20.0 -> 0.0
            total in 25_000..49_999 -> 150.0 * km
            total < 25_000 -> 300.0 * km
            else -> 150.0 * km
        }
    }

    // Mostrar detalle de la regla aplicada
    private fun detalleRegla(total: Long, km: Double, costo: Double): String {
        return when {
            total >= 50_000 && km <= 20.0 ->
                "Compra ≥ $50.000 y ≤ 20 km → Despacho GRATIS"
            total in 25_000..49_999 ->
                "Compra entre $25.000 y $49.999 → $150/km × $km km"
            total < 25_000 ->
                "Compra < $25.000 → $300/km × $km km"
            else ->
                "Compra ≥ $50.000 pero distancia > 20 km → $150/km × $km km"
        } + "  → Total: $${costo.toLong()}"
    }

    override fun onDestroy() {
        super.onDestroy()
        tempListener?.let { tempRef.removeEventListener(it) }
    }
}
