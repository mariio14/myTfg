package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Post;
import es.udc.fi.tfg.model.entities.Users;
import es.udc.fi.tfg.model.services.Block;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * The Class UserConversor.
 */
public class UserConversor {

	/**
	 * Instantiates a new user conversor.
	 */
	private UserConversor() {
	}

	/**
	 * To user dto.
	 *
	 * @param user the user
	 * @return the user dto
	 */
	public static final UserDto toUserDto(Users user) {
		return new UserDto(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getRole().toString(),user.getAvatar());
	}

	/**
	 * To user.
	 *
	 * @param userDto the user dto
	 * @return the user
	 */
	public static final Users toUser(UserDto userDto) {

		return new Users(userDto.getUserName(), userDto.getPassword(), userDto.getFirstName(), userDto.getLastName(),
				userDto.getEmail(),userDto.getAvatar());
	}

	public static final Users toUserAvatarAsString(UserAvatarAsStringDto userDto) {

		String image = userDto.getAvatar();
		if (image.contains(",")) {
			image = image.split(",")[1];
		}
		byte[] imageBytes = Base64.getDecoder().decode(image);

		return new Users(userDto.getUserName(), userDto.getPassword(), userDto.getFirstName(), userDto.getLastName(),
				userDto.getEmail(), imageBytes);
	}

	/**
	 * To authenticated user dto.
	 *
	 * @param serviceToken the service token
	 * @param user         the user
	 * @return the authenticated user dto
	 */
	public static final AuthenticatedUserDto toAuthenticatedUserDto(String serviceToken, Users user) {

		return new AuthenticatedUserDto(serviceToken, toUserDto(user));

	}

	public static final UserProfileDto toUserProfileDto(Users user, boolean followed, Block<Post> posts) throws IOException {

		List<PostFeedDto> postDtoList = new ArrayList<>();
		for (Post post : posts.getItems()) {
			PostFeedDto postDto = PostConversor.toPostFeedDto(post);
			postDtoList.add(postDto);
		}

		BlockDto<PostFeedDto> block= new BlockDto<>(postDtoList, posts.getExistMoreItems());

		return new UserProfileDto(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getRole().toString(),user.getAvatar(), followed, block, user.getFollowerUsers().size(), user.getFollowedUsers().size());
	}

}
