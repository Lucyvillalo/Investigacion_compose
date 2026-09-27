package sv.edu.udb.perfilcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PerfilComposeTheme {
                PantallaPerfil()
            }
        }
    }
}

@Composable
fun PerfilComposeTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}

@Composable
fun PantallaPerfil() {
    var siguiendo by rememberSaveable { mutableStateOf(false) }
    var contadorLikes by rememberSaveable { mutableIntStateOf(24) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Perfil", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(20.dp))
            TarjetaPerfil(
                nombre = "Ana Martínez",
                descripcion = "Estudiante de Ingeniería en Sistemas",
                likes = contadorLikes,
                siguiendo = siguiendo,
                onSeguirClick = { siguiendo = !siguiendo },
                onLikeClick = { contadorLikes++ }
            )
        }
    }
}

@Composable
fun TarjetaPerfil(
    nombre: String,
    descripcion: String,
    likes: Int,
    siguiendo: Boolean,
    onSeguirClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = "Tarjeta de perfil de $nombre"
            }
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF153A6F))
                    .semantics {
                        contentDescription = "Avatar de $nombre"
                    }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(nombre, style = MaterialTheme.typography.titleLarge)
            Text(descripcion, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onSeguirClick) {
                    Text(if (siguiendo) "Siguiendo" else "Seguir")
                }
                OutlinedButton(onClick = onLikeClick) {
                    Text("Me gusta ($likes)")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPerfilPreview() {
    PerfilComposeTheme {
        PantallaPerfil()
    }
}
