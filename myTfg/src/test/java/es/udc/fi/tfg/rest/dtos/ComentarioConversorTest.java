package es.udc.fi.tfg.rest.dtos;


import es.udc.fi.tfg.model.entities.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ComentarioConversorTest {


    @Test
    public void testToComentarioDto() {
        Post post = getValidPost();
        Users user = getValidUser();
        Comentario comentario = new Comentario( "Texto de prueba", null, post, user);

        ComentarioDto comentarioDto = ComentarioConversor.toComentarioDto(comentario);

        assertEquals(comentario.getId(), comentarioDto.getId());
        assertEquals(comentario.getTextoComentario(), comentarioDto.getTextoComentario());
        assertEquals(comentario.getUser().getUserName(), comentarioDto.getUserName());
        assertEquals(comentario.getUser().getId(), comentarioDto.getUserId());
        assertEquals(comentario.getPost().getId(), comentarioDto.getPostId());
        assertEquals(comentario.getComentarioPadre() == null ? null : comentario.getComentarioPadre().getId(),
                comentarioDto.getComentarioPadreId());
        assertArrayEquals(comentario.getUser().getAvatar(), comentarioDto.getAvatar());
    }

    @Test
    public void testToComentariosDto() {
        Post post = getValidPost();
        Users user = getValidUser();
        // Crear una lista de Comentario para probar
        List<Comentario> comentarios = Arrays.asList(
                new Comentario( "Texto de prueba", null, post, user),
                new Comentario("Texto de prueba 2", null, post, user),
                new Comentario( "Texto de prueba 3", null, post, user)
        );

        // Llamar al método de conversión
        List<ComentarioDto> comentariosDto = ComentarioConversor.toComentariosDto(comentarios);

        // Verificar que se ha convertido la lista correctamente
        assertEquals(comentarios.size(), comentariosDto.size());

        for (int i = 0; i < comentarios.size(); i++) {
            assertEquals(comentarios.get(i).getId(), comentariosDto.get(i).getId());
            assertEquals(comentarios.get(i).getTextoComentario(), comentariosDto.get(i).getTextoComentario());
            assertEquals(comentarios.get(i).getUser().getUserName(), comentariosDto.get(i).getUserName());
            assertEquals(comentarios.get(i).getUser().getId(), comentariosDto.get(i).getUserId());
            assertEquals(comentarios.get(i).getPost().getId(), comentariosDto.get(i).getPostId());
            assertEquals(comentarios.get(i).getComentarioPadre() == null ? null :
                            comentarios.get(i).getComentarioPadre().getId(),
                    comentariosDto.get(i).getComentarioPadreId());
            assertArrayEquals(comentarios.get(i).getUser().getAvatar(), comentariosDto.get(i).getAvatar());
        }
    }
    private Post getValidPost(){
        return new Post(getValidUser(), "titulo", "description", "2022/2023", LocalDateTime.now(), new Subject("name", new University("name")));
    }
    private Users getValidUser(){
        return new Users("nombre", "pass", "a", "b", "d@gmail.com");
    }

}

 
