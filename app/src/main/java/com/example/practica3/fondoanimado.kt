package com.example.practica3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlin.random.Random

class Ballon(
    initialPos: Offset,
    var velocity: Offset,
    val size: Float,
    val image: ImageBitmap
) {
    var pos by mutableStateOf(initialPos)
}

@Composable
fun FondoAnimadoContenido() {
    val tamanoBalon = 130f
    val balonImage = ImageBitmap.imageResource(id = R.drawable.balon_futbol)

    val ballons = remember {
        List(10) { // Reducimos a 10 para que los choques se vean mejor y no se saturen
            Ballon(
                initialPos = Offset(
                    Random.nextFloat() * 600f + 50f,
                    Random.nextFloat() * 1000f + 50f
                ),
                velocity = Offset(
                    Random.nextFloat() * 6f - 3f,
                    Random.nextFloat() * 6f - 3f
                ),
                size = tamanoBalon,
                image = balonImage
            )
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos { _ ->
                // 1. Mover cada balón
                ballons.forEach { ballon ->
                    ballon.pos += ballon.velocity
                }

                // 2. Detectar colisiones entre balones
                for (i in ballons.indices) {
                    for (j in i + 1 until ballons.size) {
                        val b1 = ballons[i]
                        val b2 = ballons[j]

                        // Centros de cada balón
                        val center1 = b1.pos + Offset(b1.size / 2f, b1.size / 2f)
                        val center2 = b2.pos + Offset(b2.size / 2f, b2.size / 2f)

                        val delta = center2 - center1
                        val distance = delta.getDistance()
                        val minDist = b1.size // Como ambos tienen el mismo tamaño, la distancia mínima es su tamaño

                        if (distance < minDist && distance > 0f) {
                            // Hay colisión: calculamos la normal
                            val overlap = minDist - distance
                            val normal = delta / distance

                            // Separar los balones para que no se queden trabados
                            b1.pos -= normal * (overlap / 2f)
                            b2.pos += normal * (overlap / 2f)

                            // Intercambio de velocidades (rebote elástico básico)
                            val kx = b1.velocity.x - b2.velocity.x
                            val ky = b1.velocity.y - b2.velocity.y
                            val p = 2f * (normal.x * kx + normal.y * ky) / 2f

                            b1.velocity = b1.velocity - normal * p
                            b2.velocity = b2.velocity + normal * p
                        }
                    }
                }
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        ballons.forEach { ballon ->
            // 3. Rebote en los bordes de la pantalla
            if (ballon.pos.x < 0) {
                ballon.pos = ballon.pos.copy(x = 0f)
                ballon.velocity = ballon.velocity.copy(x = -ballon.velocity.x)
            } else if (ballon.pos.x + ballon.size > canvasWidth) {
                ballon.pos = ballon.pos.copy(x = canvasWidth - ballon.size)
                ballon.velocity = ballon.velocity.copy(x = -ballon.velocity.x)
            }

            if (ballon.pos.y < 0) {
                ballon.pos = ballon.pos.copy(y = 0f)
                ballon.velocity = ballon.velocity.copy(y = -ballon.velocity.y)
            } else if (ballon.pos.y + ballon.size > canvasHeight) {
                ballon.pos = ballon.pos.copy(y = canvasHeight - ballon.size)
                ballon.velocity = ballon.velocity.copy(y = -ballon.velocity.y)
            }

            // Dibujar balón
            drawImage(
                image = ballon.image,
                dstOffset = IntOffset(ballon.pos.x.toInt(), ballon.pos.y.toInt()),
                dstSize = IntSize(ballon.size.toInt(), ballon.size.toInt())
            )
        }
    }
}