package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoResponse
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoResponseMapper: Mapper<Topico, TopicoResponse> {

    override fun map(topico: Topico): TopicoResponse {
        return TopicoResponse(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
    }
}