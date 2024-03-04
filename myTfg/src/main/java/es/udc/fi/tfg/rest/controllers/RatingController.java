package es.udc.fi.tfg.rest.controllers;

import java.util.Locale;

import es.udc.fi.tfg.model.entities.Rating;
import es.udc.fi.tfg.model.services.RatingService;
import es.udc.fi.tfg.model.services.exceptions.InvalidRatingException;
import es.udc.fi.tfg.model.services.exceptions.NoRatingException;
import es.udc.fi.tfg.rest.common.ErrorsDto;
import es.udc.fi.tfg.rest.dtos.RatingConversor;
import es.udc.fi.tfg.rest.dtos.RatingDto;
import es.udc.fi.tfg.rest.dtos.UploadRatingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;

/**
 * The Class RatingController.
 */
@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	
	
	/** The Constant INVALID_RATING_EXCEPTION_CODE. */
	private final static String INVALID_RATING_EXCEPTION_CODE = "El rating ha de ser válido.";
	

    /** The message source. */
    @Autowired
    private MessageSource messageSource;
	
	/** The rating service. */
	@Autowired
	private RatingService ratingService;
	
	
	
	/**
	 * Handle invalid rating exception.
	 *
	 * @param exception the exception
	 * @param locale the locale
	 * @return the errors dto
	 */
	@ExceptionHandler(NoRatingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleInvalidRatingException(InvalidRatingException exception, Locale locale){
        String errorMessage = messageSource.getMessage(INVALID_RATING_EXCEPTION_CODE, null,
        		INVALID_RATING_EXCEPTION_CODE, locale);
        return new ErrorsDto(errorMessage);
    }
	
	
	/**
	 * Upload rating.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @param params the params
	 * @return the rating dto
	 * @throws InstanceNotFoundException the instance not found exception
	 * @throws InvalidRatingException the invalid rating exception
	 */
	@PostMapping("/upload/{id}")
	public RatingDto uploadRating(@PathVariable Long id, @RequestAttribute Long userId,
                                  @Validated @RequestBody UploadRatingDto params)
			throws InstanceNotFoundException, InvalidRatingException{

		Rating rating = ratingService.postRating(params.getRating(), userId, id);

		return RatingConversor.toRatingDto(rating);
	}


	
	/**
	 * Delete rating.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteRating(@PathVariable Long id, @RequestAttribute Long userId) throws InstanceNotFoundException {
		ratingService.deleteRating(id, userId);
	}
	
}