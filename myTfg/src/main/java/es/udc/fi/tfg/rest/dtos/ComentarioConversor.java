package es.udc.fi.tfg.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.fi.tfg.model.entities.Comentario;

public class ComentarioConversor {

    public static final List<ComentarioDto> toComentariosDto(List<Comentario> comentarios) {
        return comentarios.stream().map(o -> toComentarioDto(o)).collect(Collectors.toList());
    }

    public static final ComentarioDto toComentarioDto(Comentario comentario) {

        return new ComentarioDto(comentario.getId(), comentario.getTextoComentario(),
                comentario.getUser().getUserName(),
                comentario.getUser().getId(), comentario.getPost().getId(),
                comentario.getComentarioPadre() == null ? null : comentario.getComentarioPadre().getId(),
                comentario.getUser().getAvatar());
    }

}
