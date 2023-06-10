package app.tiktok.repositores;

import app.tiktok.tables.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByAccountId(String accountId);
    Likes findByAccountIdAndNameAccountLikesVideoAndIdVideo(String accountId, String nameAccountLikesVideo, String idVideo);
    Likes findByAccountId(String accountId);
}
