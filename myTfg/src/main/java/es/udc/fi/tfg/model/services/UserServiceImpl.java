package es.udc.fi.tfg.model.services;

import java.util.Optional;

import es.udc.fi.tfg.model.entities.FollowedUser;
import es.udc.fi.tfg.model.entities.FollowedUserDao;
import es.udc.fi.tfg.model.entities.UserDao;
import es.udc.fi.tfg.model.entities.Users;
import es.udc.fi.tfg.model.services.exceptions.AlreadyFollowingException;
import es.udc.fi.tfg.model.services.exceptions.NotFollowingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.services.exceptions.IncorrectLoginException;
import es.udc.fi.tfg.model.services.exceptions.IncorrectPasswordException;

/**
 * The Class UserServiceImpl.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	/** The permission checker. */
	@Autowired
	private PermissionChecker permissionChecker;

	/** The password encoder. */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	@Autowired
	private FollowedUserDao followedUserDao;

	/**
	 * Sign up.
	 *
	 * @param user the user
	 * @throws DuplicateInstanceException the duplicate instance exception
	 */
	@Override
	public void signUp(Users user) throws DuplicateInstanceException {

		if (userDao.existsByUserName(user.getUserName())) {
			throw new DuplicateInstanceException("project.entities.user", user.getUserName());
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Users.RoleType.USER);

		userDao.save(user);

	}

	/**
	 * Login.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the user
	 * @throws IncorrectLoginException the incorrect login exception
	 */
	@Override
	@Transactional(readOnly = true)
	public Users login(String userName, String password) throws IncorrectLoginException {

		Optional<Users> user = userDao.findByUserName(userName);

		if (!user.isPresent()) {
			throw new IncorrectLoginException(userName, password);
		}

		if (!passwordEncoder.matches(password, user.get().getPassword())) {
			throw new IncorrectLoginException(userName, password);
		}

		return user.get();

	}

	/**
	 * Login from id.
	 *
	 * @param id the id
	 * @return the user
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	@Override
	@Transactional(readOnly = true)
	public Users loginFromId(Long id) throws InstanceNotFoundException {
		return permissionChecker.checkUser(id);
	}

	/**
	 * Update profile.
	 *
	 * @param id        the id
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param email     the email
	 * @return the user
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	@Override
	public Users updateProfile(Long id, String firstName, String lastName, String email, byte[] avatar)
			throws InstanceNotFoundException {

		Users user = permissionChecker.checkUser(id);

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAvatar(avatar);

		return user;

	}

	/**
	 * Change password.
	 *
	 * @param id          the id
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @throws InstanceNotFoundException  the instance not found exception
	 * @throws IncorrectPasswordException the incorrect password exception
	 */
	@Override
	public void changePassword(Long id, String oldPassword, String newPassword)
			throws InstanceNotFoundException, IncorrectPasswordException {

		Users user = permissionChecker.checkUser(id);

		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new IncorrectPasswordException();
		} else {
			user.setPassword(passwordEncoder.encode(newPassword));
		}

	}

	@Override
	public Users getUserProfile(Long id)
			throws InstanceNotFoundException {

		Optional<Users> usersOptional =  userDao.findById(id);

		if (usersOptional.isEmpty())
			throw new InstanceNotFoundException("project.entities.user", id);

		return usersOptional.get();
	}


	@Override
	public void followUser(Long userId, Long userToFollowId) throws AlreadyFollowingException, InstanceNotFoundException {

		Users user = permissionChecker.checkUser(userId);

		Optional<Users> usersOptional = userDao.findById(userToFollowId);

		if(usersOptional.isEmpty()) {
			throw new InstanceNotFoundException("project.entities.users", userToFollowId);
		}

		Optional<FollowedUser> optFollowedUser = followedUserDao.findByFollowed_IdAndFollower_Id(userToFollowId, userId);

		if (!optFollowedUser.isEmpty()) {
			throw new AlreadyFollowingException("Ya esta siguiendo a este usuario");
		}

		FollowedUser followedUser = new FollowedUser(user, usersOptional.get());

		followedUserDao.save(followedUser);
	}


	@Override
	public void unfollowUser(Long userId, Long userToUnfollowId) throws InstanceNotFoundException, NotFollowingException {
		Users user = permissionChecker.checkUser(userId);

		Optional<Users> usersOptional = userDao.findById(userToUnfollowId);
		if(usersOptional.isEmpty()) {
			throw new InstanceNotFoundException("project.entities.users", userToUnfollowId);
		}
		Users unfollowed = usersOptional.get();

		Optional<FollowedUser> optFollowedUser = followedUserDao.findByFollowed_IdAndFollower_Id(userId, userToUnfollowId);

		if (optFollowedUser.isEmpty()) {
			throw new NotFollowingException("No esta siguiendo a este usuario");
		}

		FollowedUser followedUser1 = optFollowedUser.get();

		followedUserDao.delete(followedUser1);

		unfollowed.getFollowerUsers().removeIf(followedUser -> followedUser.equals(followedUser1));
		user.getFollowedUsers().removeIf(followedUser -> followedUser.equals(followedUser1));
	}

	@Override
	public boolean userFollows(Long userId, Long userFollowingId) {
		Optional<FollowedUser> optionalFollowedUser = followedUserDao.findByFollowed_IdAndFollower_Id(userId, userFollowingId);

		return optionalFollowedUser.isPresent();
	}
}
