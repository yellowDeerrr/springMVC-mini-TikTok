package app.tiktok.repositores;

import app.tiktok.tables.UsersAccount;
import app.tiktok.tables.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersAccountRepository extends JpaRepository<UsersAccount, Long> {
    UsersAccount findByLogin(String login);
    UsersAccount findByLoginAndPassword(String login, String password);
    UsersAccount findByPhotoId(String photoId);
    List<UsersAccount> findByUserName(String userName);

}
