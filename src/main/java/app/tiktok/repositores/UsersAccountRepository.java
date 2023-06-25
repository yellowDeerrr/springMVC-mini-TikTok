package app.tiktok.repositores;

import app.tiktok.tables.UsersAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersAccountRepository extends JpaRepository<UsersAccount, Long> {
    UsersAccount findByLogin(String login);
    UsersAccount findByLoginAndPasswordAndUserName(String login, String password, String userName);
    UsersAccount findByPhotoId(String photoId);
    UsersAccount findByUserName(String userName);

}
