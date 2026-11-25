package com.example.parcial2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial2.ui.theme.Parcial2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DosPantallasCompose(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DosPantallasCompose(modifier: Modifier = Modifier) {
    var pantalla by remember { mutableIntStateOf(1) }
    var nombreGuardado by remember { mutableStateOf("") }
    var correoGuardado by remember { mutableStateOf("") }

    when (pantalla) {
        1 -> PantallaFormularioCompose(
            modifier = modifier,
            nombreInicial = nombreGuardado,
            correoInicial = correoGuardado,
            onContinuar = { nombre, correo ->
                nombreGuardado = nombre
                correoGuardado = correo
                pantalla = 2
            }
        )
        2 -> PantallaResumenCompose(
            modifier = modifier,
            nombre = nombreGuardado,
            correo = correoGuardado,
            onVolver = { pantalla = 1 }
        )
    }
}

@Composable
fun PantallaFormularioCompose(
    modifier: Modifier = Modifier,
    nombreInicial: String,
    correoInicial: String,
    onContinuar: (String, String) -> Unit
) {
    var nombre by remember { mutableStateOf(nombreInicial) }
    var correo by remember { mutableStateOf(correoInicial) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Formulario - Pantalla 1",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onContinuar(nombre.trim(), correo.trim()) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Resumen")
        }
    }
}

@Composable
fun PantallaResumenCompose(
    modifier: Modifier = Modifier,
    nombre: String,
    correo: String,
    onVolver: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Resumen - Pantalla 2",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(text = "Nombre: ${nombre.ifEmpty { "Sin dato" }}")
        Text(text = "Correo: ${correo.ifEmpty { "Sin dato" }}")

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al formulario")
        }
    }
}