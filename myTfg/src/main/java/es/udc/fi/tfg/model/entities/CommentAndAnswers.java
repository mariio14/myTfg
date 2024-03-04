package es.udc.fi.tfg.model.entities;

import java.util.List;

public class CommentAndAnswers {
	
	private Comentario comentario;
	
	private List<Comentario> respuestas;

	public CommentAndAnswers(Comentario comentario, List<Comentario> respuestas) {
		this.comentario = comentario;
		this.respuestas = respuestas;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public List<Comentario> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Comentario> respuestas) {
		this.respuestas = respuestas;
	}
}