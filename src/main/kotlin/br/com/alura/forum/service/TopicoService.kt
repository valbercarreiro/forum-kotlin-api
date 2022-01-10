package br.com.alura.forum.service

import br.com.alura.forum.dto.AlterarTopicoDTO
import br.com.alura.forum.dto.TopicoDTO
import br.com.alura.forum.dto.TopicoResponse
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoDTOMapper
import br.com.alura.forum.mapper.TopicoResponseMapper
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
                    private val repository: TopicoRepository,
                    private val topicoResponseMapper: TopicoResponseMapper,
                    private val topicoDTOMapper: TopicoDTOMapper,
                    private val notFoundMessage: String = "Tópico não encontrado!"
                    ) {

    fun listar(): List<TopicoResponse> {
        return repository.findAll().stream().map {
            t -> topicoResponseMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoResponse {
        val topico = repository.findById(id)
                .orElseThrow { NotFoundException(notFoundMessage) }

        return topicoResponseMapper.map(topico)
    }

    fun cadastrar(dto: TopicoDTO): TopicoResponse {
        val topico = topicoDTOMapper.map(dto)
        repository.save(topico)
        return topicoResponseMapper.map(topico)
    }

    fun alterar(id: Long, dto: AlterarTopicoDTO): TopicoResponse {
        val topico = repository.findById(id)
                .orElseThrow { NotFoundException(notFoundMessage) }

        topico.titulo = dto.titulo
        topico.mensagem = dto.mensagem
        repository.save(topico)

        return return topicoResponseMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}