package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoDTO
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoDTOMapper(private var cursoService: CursoService,
                      private var usuarioService: UsuarioService
                      ): Mapper<TopicoDTO, Topico> {

    override fun map(dto: TopicoDTO): Topico {
        return Topico(
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor)
        )
    }

}