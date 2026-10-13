package com.example.practica3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica3.ui.theme.Practica3Theme

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

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "VistaInicioSesion") {
        composable("VistaMenu") { VistaMenu(navController, modifier) }
        composable("VistaInicioSesion") { VistaInicioSesion(navController, modifier) }
    }
}
@Composable
fun VistaMenu(navController: NavHostController, modifier: Modifier) {
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
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Menu",
            fontSize = 36.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            style = TextStyle(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color.Black,
                    blurRadius = 16f,
                    offset = androidx.compose.ui.geometry.Offset(0f, 0f)
                )
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        )
        Button(

            onClick = {
                navController.navigate("VistaInicioSesion")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Inicio de sesion",
                fontSize = 17.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.White,
                        blurRadius = 16f,
                        offset = androidx.compose.ui.geometry.Offset(0f, 0f)
                    )


                )
            )
        }

        Button(

            onClick = {
                navController.navigate("VistaInicioSesion")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Entrenadores",
                fontSize = 17.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.White,
                        blurRadius = 16f,
                        offset = androidx.compose.ui.geometry.Offset(0f, 0f)
                    )


                )
            )
        }

        Button(

            onClick = {
                navController.navigate("VistaInicioSesion")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Jugadores",
                fontSize = 17.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.White,
                        blurRadius = 16f,
                        offset = androidx.compose.ui.geometry.Offset(0f, 0f)
                    )


                )
            )
        }

        Button(

            onClick = {
                navController.navigate("VistaInicioSesion")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Ligas",
                fontSize = 17.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.White,
                        blurRadius = 16f,
                        offset = androidx.compose.ui.geometry.Offset(0f, 0f)
                    )


                )
            )
        }

        Button(

            onClick = {
                navController.navigate("VistaInicioSesion")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Clubs",
                fontSize = 17.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.White,
                        blurRadius = 16f,
                        offset = androidx.compose.ui.geometry.Offset(0f, 0f)
                    )


                )
            )
        }

        Button(

            onClick = {
                navController.navigate("VistaInicioSesion")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Selecciones Nacionales",
                fontSize = 17.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                style = TextStyle(
                    shadow = androidx.compose.ui.graphics.Shadow(
                        color = Color.White,
                        blurRadius = 16f,
                        offset = androidx.compose.ui.geometry.Offset(0f, 0f)
                    )


                )
            )
        }

    }
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
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
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
