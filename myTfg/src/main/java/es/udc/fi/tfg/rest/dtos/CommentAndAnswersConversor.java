package es.udc.fi.tfg.rest.dtos;

import java.util.ArrayList;
import java.util.List;

import es.udc.fi.tfg.model.entities.CommentAndAnswers;
import es.udc.fi.tfg.model.services.Block;

public class CommentAndAnswersConversor {
	
	public static final BlockDto<CommentAndAnswersDto> toCommentAndAnswersDtos(Block<CommentAndAnswers> block) {
		List<CommentAndAnswersDto> list = new ArrayList<>();
		
		for(CommentAndAnswers comment : block.getItems()) {
			list.add(toCommentAndAnswersDto(comment));
		}
			
		return new BlockDto<>(list, block.getExistMoreItems());
	}
	
	public static final CommentAndAnswersDto toCommentAndAnswersDto(CommentAndAnswers comment) {
		return new CommentAndAnswersDto(ComentarioConversor.toComentarioDto(comment.getComentario()),
				ComentarioConversor.toComentariosDto(comment.getRespuestas()));
	}
}