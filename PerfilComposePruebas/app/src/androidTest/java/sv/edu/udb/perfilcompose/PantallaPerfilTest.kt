package sv.edu.udb.perfilcompose

import androidx.compose.ui.test.assertExists
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class PantallaPerfilTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun cargarPantalla() {
        composeTestRule.setContent {
            PerfilComposeTheme {
                PantallaPerfil()
            }
        }
    }

    @Test
    fun muestraDatosIniciales() {
        cargarPantalla()

        composeTestRule.onNodeWithText("Ana Martínez").assertExists()
        composeTestRule.onNodeWithText("Seguir").assertExists()
        composeTestRule.onNodeWithText("Me gusta (24)").assertExists()
    }

    @Test
    fun seguirCambiaElTexto() {
        cargarPantalla()

        composeTestRule.onNodeWithText("Seguir").performClick()
        composeTestRule.onNodeWithText("Siguiendo").assertExists()
    }

    @Test
    fun meGustaIncrementaElContador() {
        cargarPantalla()

        composeTestRule.onNodeWithText("Me gusta (24)").performClick()
        composeTestRule.onNodeWithText("Me gusta (25)").assertExists()
    }
}
