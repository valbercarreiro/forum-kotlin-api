package br.com.alura.forum.repository

import br.com.alura.forum.dto.TopicoPorCategoriaDTO
import br.com.alura.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeCurso: String, paginacao: Pageable): Page<Topico>

    @Query("SELECT new br.com.alura.forum.dto.TopicoPorCategoriaDTO(curso.categoria, count(t)) from Topico t JOIN t.curso c GROUP BY c.categoria")
    fun relatorio(): List<TopicoPorCategoriaDTO>

}