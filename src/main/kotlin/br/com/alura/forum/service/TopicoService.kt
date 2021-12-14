package br.com.alura.forum.service

import br.com.alura.forum.dto.AlterarTopicoDTO
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

    fun cadastrar(dto: TopicoDTO): TopicoResponse {
        val topico = topicoDTOMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)

        return topicoResponseMapper.map(topico)
    }

    fun alterar(id: Long, dto: AlterarTopicoDTO): TopicoResponse {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        val topicoAlterado = Topico(
                            id = id,
                            titulo = dto.titulo,
                            mensagem = dto.mensagem,
                            autor = topico.autor,
                            curso = topico.curso,
                            respostas = topico.respostas,
                            status = topico.status,
                            dataCriacao = topico.dataCriacao
                        )

        topicos = topicos.minus(topico).plus(topicoAlterado)

        return return topicoResponseMapper.map(topicoAlterado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        topicos = topicos.minus(topico)
    }
}