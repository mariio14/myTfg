package es.udc.fi.tfg.rest.controllers;

import java.util.Locale;

import es.udc.fi.tfg.model.entities.Comentario;
import es.udc.fi.tfg.model.entities.CommentAndAnswers;
import es.udc.fi.tfg.model.services.Block;
import es.udc.fi.tfg.model.services.CommentService;
import es.udc.fi.tfg.rest.common.ErrorsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.MessageSource;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.services.exceptions.InvalidCommentException;
import es.udc.fi.tfg.rest.dtos.BlockDto;
import es.udc.fi.tfg.rest.dtos.ComentarioConversor;
import es.udc.fi.tfg.rest.dtos.ComentarioDto;
import es.udc.fi.tfg.rest.dtos.CommentAndAnswersConversor;
import es.udc.fi.tfg.rest.dtos.CommentAndAnswersDto;
import es.udc.fi.tfg.rest.dtos.UploadAnswerParamsDto;
import es.udc.fi.tfg.rest.dtos.UploadComentarioParamsDto;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	private final static String INVALID_COMMENT_EXCEPTION_CODE = "No esta permitido un comentario vacio.";

	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private CommentService commentService;
	
	@ExceptionHandler(InvalidCommentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleAlreadyExistingAnswerException(InvalidCommentException exception, Locale locale){
        String errorMessage = messageSource.getMessage(INVALID_COMMENT_EXCEPTION_CODE, null,
        		INVALID_COMMENT_EXCEPTION_CODE, locale);
        return new ErrorsDto(errorMessage);
    }
	
	
	@PostMapping("/uploadComment/{id}")
	public ComentarioDto uploadComment(@PathVariable Long id, @RequestAttribute Long userId,
                                       @Validated @RequestBody UploadComentarioParamsDto params)
			throws InstanceNotFoundException, InvalidCommentException {

		Comentario comentario = commentService.postComentario(params.getTextoComentario(), id, userId);
		return ComentarioConversor.toComentarioDto(comentario);
	}

	@PostMapping("/uploadAnswer/{commentId}")
	public ComentarioDto uploadAnswerToComment(@PathVariable Long commentId,
			@RequestAttribute Long userId,
			@Validated @RequestBody UploadAnswerParamsDto params)
			throws InstanceNotFoundException, InvalidCommentException {

		Comentario respuesta = commentService.postRespuestaAComentario(params.getTextoComentario(), userId,
								commentId, params.getRespuestaId());
		return ComentarioConversor.toComentarioDto(respuesta);
	}


	@GetMapping("/commentsPost/{id}")
	public BlockDto<CommentAndAnswersDto> getAllComments(@PathVariable Long id, @RequestParam(defaultValue = "0") int page)
			throws InstanceNotFoundException {

		Block<CommentAndAnswers> commentBlock = commentService.findCommentAnswersByPost(id, page, 2);

		return CommentAndAnswersConversor.toCommentAndAnswersDtos(commentBlock);
	}
	

	@DeleteMapping("/deleteComment/{id}")
	public void deleteComment(@PathVariable Long id, @RequestAttribute Long userId) throws InstanceNotFoundException {
		commentService.borrarComentario(id, userId);
	}
}