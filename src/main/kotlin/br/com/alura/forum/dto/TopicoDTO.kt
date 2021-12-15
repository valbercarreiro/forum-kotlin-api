package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoDTO (
    @field:NotEmpty @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 200 caracteres")
    val titulo: String,
    @field:NotEmpty @field:Size(min = 5, max = 200, message = "Mensagem deve ter entre 5 e 200 caracteres")
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idAutor: Long
)
