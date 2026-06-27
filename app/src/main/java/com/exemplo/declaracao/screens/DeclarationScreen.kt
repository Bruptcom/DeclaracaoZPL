package com.exemplo.declaracao.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.exemplo.declaracao.data.DeclarationData
import com.exemplo.declaracao.network.RetrofitInstance
import com.exemplo.declaracao.utils.generateDeclarationPdf
import kotlinx.coroutines.launch

@Composable
fun DeclarationScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var remetenteNome by remember { mutableStateOf("") }
    var remetenteCep by remember { mutableStateOf("") }
    var remetenteEndereco by remember { mutableStateOf("") }
    var remetenteNumero by remember { mutableStateOf("") }
    var remetenteComplemento by remember { mutableStateOf("") }
    var remetenteReferencia by remember { mutableStateOf("") }

    var destinatarioNome by remember { mutableStateOf("") }
    var destinatarioCep by remember { mutableStateOf("") }
    var destinatarioEndereco by remember { mutableStateOf("") }
    var destinatarioNumero by remember { mutableStateOf("") }
    var destinatarioComplemento by remember { mutableStateOf("") }
    var destinatarioReferencia by remember { mutableStateOf("") }

    var produtoNome by remember { mutableStateOf("") }
    var produtoPeso by remember { mutableStateOf("") }
    var produtoValor by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text("Remetente", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = remetenteNome, onValueChange = { remetenteNome = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = remetenteCep, onValueChange = { 
            remetenteCep = it 
            if (it.length == 8) {
                scope.launch {
                    try {
                        val addr = RetrofitInstance.viaCepApi.getAddress(it)
                        remetenteEndereco = "${addr.logradouro}, ${addr.bairro}"
                    } catch (e: Exception) {}
                }
            }
        }, label = { Text("CEP (8 dígitos)") }, modifier = Modifier.fillMaxWidth())

        OutlinedTextField(value = remetenteEndereco, onValueChange = { remetenteEndereco = it }, label = { Text("Endereço") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = remetenteNumero, onValueChange = { remetenteNumero = it }, label = { Text("Número") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = remetenteComplemento, onValueChange = { remetenteComplemento = it }, label = { Text("Complemento / Apto") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = remetenteReferencia, onValueChange = { remetenteReferencia = it }, label = { Text("Ponto de Referência") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(24.dp))

        Text("Destinatário", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = destinatarioNome, onValueChange = { destinatarioNome = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = destinatarioCep, onValueChange = { 
            destinatarioCep = it 
            if (it.length == 8) {
                scope.launch {
                    try {
                        val addr = RetrofitInstance.viaCepApi.getAddress(it)
                        destinatarioEndereco = "${addr.logradouro}, ${addr.bairro}"
                    } catch (e: Exception) {}
                }
            }
        }, label = { Text("CEP (8 dígitos)") }, modifier = Modifier.fillMaxWidth())

        OutlinedTextField(value = destinatarioEndereco, onValueChange = { destinatarioEndereco = it }, label = { Text("Endereço") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = destinatarioNumero, onValueChange = { destinatarioNumero = it }, label = { Text("Número") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = destinatarioComplemento, onValueChange = { destinatarioComplemento = it }, label = { Text("Complemento / Apto") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = destinatarioReferencia, onValueChange = { destinatarioReferencia = it }, label = { Text("Ponto de Referência") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(24.dp))

        Text("Produto", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = produtoNome, onValueChange = { produtoNome = it }, label = { Text("Nome do Produto") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = produtoPeso, onValueChange = { produtoPeso = it }, label = { Text("Peso (kg)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = produtoValor, onValueChange = { produtoValor = it }, label = { Text("Valor (R$") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val data = DeclarationData(
                    remetenteNome, remetenteCep, remetenteEndereco, remetenteNumero,
                    remetenteComplemento, remetenteReferencia,
                    destinatarioNome, destinatarioCep, destinatarioEndereco, destinatarioNumero,
                    destinatarioComplemento, destinatarioReferencia,
                    produtoNome, produtoPeso, produtoValor
                )
                val uri = generateDeclarationPdf(data, context)
                if (uri != null) {
                    // TODO: Mostrar mensagem de sucesso ou abrir PDF
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gerar PDF da Declaração")
        }
    }
}
