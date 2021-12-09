package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicoDTO
import br.com.alura.forum.dto.TopicoResponse
import br.com.alura.forum.mapper.Mapper
import br.com.alura.forum.mapper.TopicoDTOMapper
import br.com.alura.forum.mapper.TopicoResponseMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService(
                    private var topicos: List<Topico> = ArrayList(),
                    private val topicoResponseMapper: TopicoResponseMapper,
                    private val topicoDTOMapper: TopicoDTOMapper
                    ) {

    fun listar(): List<TopicoResponse> {
        return topicos.stream().map {
            t -> topicoResponseMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoResponse {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        return topicoResponseMapper.map(topico)
    }

    fun cadastrar(dto: TopicoDTO) {
        val topico = topicoDTOMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
    }
}