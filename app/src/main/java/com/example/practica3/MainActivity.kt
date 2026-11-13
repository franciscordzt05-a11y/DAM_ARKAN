package com.example.practica3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica3.ui.theme.Practica3Theme
import android.content.Context
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field


data class ModeloCategoria(
    val id: Int,
    val nombre: String
)

data class ModeloProducto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val idCategoria: Int,
    val nombreCategoria: String
)




    interface ApiService {

        @GET("categorias.php?buscar")
        suspend fun buscarCategorias():
                List<ModeloCategoria>

        @GET("productos.php?buscar")
        suspend fun buscarProductos():
                List<ModeloProducto>

        @POST("productos.php?registrar")
        @FormUrlEncoded
        suspend fun registrarProducto(
            @Field("nombre") nombre: String,
            @Field("precio") precio: Double,
            @Field("categoria") categoria: Int
        ): Response<Unit>

        @POST("productos.php?eliminar")
        @FormUrlEncoded
        suspend fun eliminarProducto(
            @Field("id") id: Int
        ): Response<Unit>
    }

val retrofit = Retrofit.Builder()
    .baseUrl(
        "https://authorization-offers-filled-themes.trycloudflare.com/AW/ACT4/api/"
    )
    .addConverterFactory(
        ScalarsConverterFactory.create()
    )
    .addConverterFactory(
        GsonConverterFactory.create()
    )
    .build()

val api = retrofit.create(ApiService::class.java)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
class Preferencias(context: Context) {
    private val preferences = context.getSharedPreferences("app", Context.MODE_PRIVATE)


    fun guardar(prop: String, value: String) {
        preferences.edit()
            .putString(prop, value)
            .apply()
    }


    fun obtener(prop: String, def: String): String? {
        return preferences.getString(prop, def)
    }


    fun remover(prop: String) {
        preferences.edit()
            .remove(prop)
            .apply()
    }
}

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "VistaInicioSesion"
    ) {
        composable("VistaMenu") {
            VistaMenu(navController, modifier)
        }

        composable("VistaInicioSesion") {
            VistaInicioSesion(navController, modifier)
        }

        composable("Entrenadores") {
            VistaFormulario(
                navController,
                modifier,
                "Entrenadores",
                listOf(
                    "Nombre Completo",
                    "Precio",
                    "Edad",
                    "Club",
                    "Selección"
                )
            )
        }

        composable("Jugadores") {
            VistaFormulario(
                navController,
                modifier,
                "Jugadores",
                listOf(
                    "Nombre",
                    "Posición",
                    "Valor de Mercado",
                    "Edad",
                    "Club",
                    "Selección"
                )
            )
        }

        composable("Ligas") {
            VistaFormulario(
                navController,
                modifier,
                "Ligas",
                listOf(
                    "Nombre",
                    "País",
                    "División"
                )
            )
        }

        composable("Clubs") {
            VistaFormulario(
                navController,
                modifier,
                "Clubes",
                listOf(
                    "Nombre",
                    "Estadio",
                    "Año de Fundación",
                    "Liga"
                )
            )
        }

        composable("Selecciones") {
            VistaFormulario(
                navController,
                modifier,
                "Selecciones Nacionales",
                listOf(
                    "Nombre",
                    "Confederación",
                    "Ranking FIFA"
                )
            )
        }
    }
}

@Composable
fun VistaMenu(
    navController: NavHostController,
    modifier: Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.fondo_menu),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        FondoAnimadoContenido()
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Menu",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            val opciones = listOf(
                "Entrenadores" to "Entrenadores",
                "Jugadores" to "Jugadores",
                "Ligas" to "Ligas",
                "Clubs" to "Clubs",
                "Selecciones Nacionales" to "Selecciones"
            )

            opciones.forEach { (texto, ruta) ->
                Button(
                    onClick = {
                        navController.navigate(ruta)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = texto,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("VistaInicioSesion") {
                        popUpTo("VistaMenu") {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaFormulario(
    navController: NavHostController,
    modifier: Modifier,
    titulo: String,
    campos: List<String>
) {
    val context = LocalContext.current

    var valores by remember(titulo) {
        mutableStateOf(List(campos.size) { "" })
    }

    val registros = remember(titulo) {
        mutableStateListOf<List<String>>()
    }
    data class Opcion(val value: String, val label: String)
    val productoOpciones = listOf(
        Opcion("1", "ligamx"),
        Opcion("2", "laliga"),
        Opcion("3", "bundesliga"),
        Opcion("4", "premierleague")
    )
    var productoExpandido by remember { mutableStateOf(false) }
    var producto by remember { mutableStateOf(productoOpciones[0]) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Button(
            onClick = {
                navController.navigate("VistaMenu") {
                    popUpTo("VistaMenu") {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Blue
            )
        ) {
            Text("← Menú")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = titulo,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        campos.forEachIndexed { index, campo ->
            Text(
                text = "$campo:",
                fontWeight = FontWeight.Bold
            )

            TextField(
                value = valores[index],
                onValueChange = { nuevoValor ->
                    val listaModificable = valores.toMutableList()
                    listaModificable[index] = nuevoValor
                    valores = listaModificable
                },
                placeholder = {
                    Text("Ingresa $campo")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
        Button(
            onClick = {
                if (valores.any { it.isBlank() }) {
                    Toast.makeText(
                        context,
                        "Completa todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    registros.add(valores.toList())
                    valores = List(campos.size) { "" }

                    Toast.makeText(
                        context,
                        "Registro agregado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },

            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1565C0),
                contentColor = Color.White
            )
        ) {
            Text("Guardar")
        }
        Text(text = "Producto:")
        ExposedDropdownMenuBox(
            expanded = productoExpandido,
            onExpandedChange = { productoExpandido = !productoExpandido }
        ) {
            TextField(
                value = producto.label,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona una opción") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = productoExpandido)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            DropdownMenu(
                expanded = productoExpandido,
                onDismissRequest = { productoExpandido = false }
            ) {
                productoOpciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion.label) },
                        onClick = {
                            producto = opcion
                            productoExpandido = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lista de $titulo",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (registros.isEmpty()) {
            Text("Todavía no hay registros agregados.")
        } else {
            registros.forEachIndexed { index, registro ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .background(
                            if (index % 2 == 0)
                                Color(0xFFF0F0F0)
                            else
                                Color(0xFFE3EAF2)
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Registro ${index + 1}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    campos.forEachIndexed { campoIndex, campo ->
                        Text("$campo: ${registro[campoIndex]}")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            registros.removeAt(index)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC62828),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Eliminar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Total de registros: ${registros.size}",
            fontWeight     = FontWeight.Bold
        )
    }
}


    @Composable
    fun VistaInicioSesion(navController: NavHostController, modifier: Modifier) {
        Image(
            painter = painterResource(R.drawable.fondo_inicio),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        val context = LocalContext.current

        var usuario: String by remember { mutableStateOf("") }
        var contrasena: String by remember { mutableStateOf("") }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            // Aquí irán nuestras cajas de texto y botones.
            Text(
                text = "Inicio de Sesión",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Usuario:")
            TextField(
                value = usuario,
                onValueChange = { usuario = it },
                placeholder = { Text("Ingresa tu usuario") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Contraseña:")
            TextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                placeholder = { Text("Ingresa tu contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("VistaMenu")
                    Toast.makeText(context, "Usuario: $usuario", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, "Contraseña: $contrasena", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Iniciar sesión")
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    Practica3Theme {
        AppContent()
    }
}
