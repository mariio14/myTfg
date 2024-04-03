package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Apunte;
import es.udc.fi.tfg.model.entities.Post;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PostConversor.
 */
public class PostConversor{

	/**
	 * To post dto.
	 *
	 * @param post the post
	 * @return the post dto
	 */
	public static final PostDto toPostDto(Post post, List<Apunte> apuntes) throws IOException {
		int year = post.getAcademicYear()+1;
		List<byte []> files = new ArrayList<>();
		for(Apunte apunte: apuntes) {
			files.add(Files.readAllBytes(Paths.get(apunte.getStoragePath())));
		}
		return new PostDto(post.getId(),post.getUser().getId(), post.getTitle(), post.getDescription(), post.getAcademicYear() + "/" + year,
				post.getCreationDate(), files, post.getApuntes().size());
	}

	public static final PostDtoReturn toPostDtoReturn(Post post, RatingDto rating) throws IOException {
		int year = post.getAcademicYear()+1;
		List<byte []> files = new ArrayList<>();
		List<String> fileNames = new ArrayList<>();
		List<String> urls = new ArrayList<>();
		for(Apunte apunte: post.getApuntes()){
			files.add(Files.readAllBytes(Paths.get(apunte.getStoragePath())));
			fileNames.add(apunte.getName());
			urls.add(apunte.getStoragePath());
		}
		return new PostDtoReturn(post.getId(),post.getUser().getId(), post.getUser().getUserName(), post.getUser().getAvatar(), post.getTitle(), post.getDescription(),
				post.getCreationDate(), post.getAcademicYear() + "/" + year, post.getAvgRating(), rating, post.getSubject().getSubjectName(),
				post.getSubject().getUniversity().getUniName(), null, fileNames, "");
	}

	public static final PostFeedDto toPostFeedDto(Post post) throws IOException {
		int year = post.getAcademicYear()+1;
		List<byte []> files = new ArrayList<>();
		for(Apunte apunte: post.getApuntes()){
			files.add(Files.readAllBytes(Paths.get(apunte.getStoragePath())));
		}
		return new PostFeedDto(post.getId(),post.getUser().getId(), post.getUser().getUserName(), post.getUser().getAvatar(), post.getTitle(), post.getDescription(),
				post.getAcademicYear() + "/" + year, post.getCreationDate(), post.getAvgRating(), post.getSubject().getSubjectName(),
				post.getSubject().getUniversity().getUniName(), files);
	}
}