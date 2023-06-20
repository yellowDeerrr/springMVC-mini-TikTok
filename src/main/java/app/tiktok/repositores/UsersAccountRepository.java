package app.tiktok.repositores;

import app.tiktok.tables.UsersAccount;
import app.tiktok.tables.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersAccountRepository extends JpaRepository<UsersAccount, Long> {
    UsersAccount findByLogin(String login);
    UsersAccount findByLoginAndPasswordAndUserName(String login, String password, String userName);
    UsersAccount findByPhotoId(String photoId);
    UsersAccount findByUserName(String userName);

}
