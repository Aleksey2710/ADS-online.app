package pro.sky.adsonlineapp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.adsonlineapp.dto.NewPassword;
import pro.sky.adsonlineapp.dto.UserDto;
import pro.sky.adsonlineapp.exceptions.NotFoundEntityException;
import pro.sky.adsonlineapp.model.User;
import pro.sky.adsonlineapp.repository.UserRepository;
import pro.sky.adsonlineapp.service.PictureService;
import pro.sky.adsonlineapp.service.UserService;
import pro.sky.adsonlineapp.utils.UserMapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static pro.sky.adsonlineapp.constants.Message.*;


/**
 * Бизнес-логика по работе с пользователями.
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PictureService pictureService;
    private final UserRepository userRepository;
    private final UserMapperUtils userMapper;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public boolean setPassword(NewPassword password, String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if (encoder.matches(password.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(password.getNewPassword()));
            userRepository.save(user);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    public UserDto getUser(String username) {

        User user = userRepository.findByUsername(username);
        if (user != null) {

            return userMapper.mapToDto(user);
        } else {
            throw new NotFoundEntityException(NOT_FOUND_ENTITY);
        }
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto user, String username) {

        User userDB = userRepository.findByUsername(username);
        userDB.setId(user.getId());
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setPhone(user.getPhone());
        userDB.setImage(user.getImage());
        userDB.setEmail(user.getEmail());

        userRepository.save(userDB);

        return userMapper.mapToDto(userDB);

    }

    @Override
    public boolean updateUserImage(String username, MultipartFile image) {

        String imageId = pictureService.addImage(image);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        user.setImage(imageId);
        userRepository.save(user);

        return true;
    }
}

