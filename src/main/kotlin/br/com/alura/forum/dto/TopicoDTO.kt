package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoDTO (
    @field:NotEmpty @field:Size(min = 5, max = 100)
    val titulo: String,
    @field:NotEmpty @field:Size(min = 5, max = 200)
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idAutor: Long
)
