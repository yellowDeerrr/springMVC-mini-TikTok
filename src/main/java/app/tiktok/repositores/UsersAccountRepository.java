package app.tiktok.repositores;

import app.tiktok.tables.UsersAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersAccountRepository extends JpaRepository<UsersAccount, Long> {
    UsersAccount findByLogin(String login);
    UsersAccount findByLoginAndPassword(String login, String password);
    UsersAccount findByPhotoId(String photoId);
}
