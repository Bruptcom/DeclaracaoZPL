package com.exemplo.declaracao.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import com.exemplo.declaracao.data.DeclarationData
import java.io.File
import java.io.FileOutputStream

fun generateDeclarationPdf(data: DeclarationData, context: Context): Uri? {
    return try {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas
        val paint = Paint().apply { textSize = 14f; isAntiAlias = true }

        var y = 50f
        canvas.drawText("DECLARAÇÃO DE CONTEÚDO", 50f, y, paint); y += 40
        canvas.drawText("Remetente: ${data.remetenteNome}", 50f, y, paint); y += 25
        canvas.drawText("Endereço: ${data.remetenteEndereco}, ${data.remetenteNumero}", 50f, y, paint); y += 25
        canvas.drawText("CEP: ${data.remetenteCep}", 50f, y, paint); y += 40

        canvas.drawText("Destinatário: ${data.destinatarioNome}", 50f, y, paint); y += 25
        canvas.drawText("Endereço: ${data.destinatarioEndereco}, ${data.destinatarioNumero}", 50f, y, paint); y += 25
        canvas.drawText("CEP: ${data.destinatarioCep}", 50f, y, paint); y += 40

        canvas.drawText("Produto: ${data.produtoNome}", 50f, y, paint); y += 25
        canvas.drawText("Peso: ${data.produtoPeso} kg", 50f, y, paint); y += 25
        canvas.drawText("Valor: R$ ${data.produtoValor}", 50f, y, paint)

        pdfDocument.finishPage(page)

        val file = File(context.getExternalFilesDir(null), "declaracao_${System.currentTimeMillis()}.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
