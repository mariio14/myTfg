package es.udc.fi.tfg.rest.dtos;

import java.util.List;

public class CommentAndAnswersDto{
	
	private ComentarioDto comentario;
	
	private List<ComentarioDto> respuestas;

	public CommentAndAnswersDto(ComentarioDto comentario, List<ComentarioDto> respuestas) {
		this.comentario = comentario;
		this.respuestas = respuestas;
	}

	public ComentarioDto getComentario() {
		return comentario;
	}

	public void setComentario(ComentarioDto comentario) {
		this.comentario = comentario;
	}

	public List<ComentarioDto> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<ComentarioDto> respuestas) {
		this.respuestas = respuestas;
	}

	
}