package br.com.alura.forum.controller

import br.com.alura.forum.dto.AlterarTopicoDTO
import br.com.alura.forum.dto.TopicoDTO
import br.com.alura.forum.dto.TopicoResponse
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.TopicoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(
            @RequestParam(required = false) nomeCurso: String?,
            @PageableDefault(size = 5, sort = ["data_criacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoResponse> {
        return service.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoResponse {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: TopicoDTO,
                    uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoResponse> {
        val topicoResponse = service.cadastrar(dto)
        val uri = uriBuilder.path("/topicos/${topicoResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoResponse)
    }

    @PutMapping("/{id}")
    @Transactional
    fun alterar(@PathVariable id: Long,
                @RequestBody @Valid dto: AlterarTopicoDTO): ResponseEntity<TopicoResponse> {
        val topicoResponse = service.alterar(id, dto)
        return ResponseEntity.ok().body(topicoResponse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}