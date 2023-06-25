package app.tiktok.repositores;

import app.tiktok.tables.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByAccountNameAndIdVideo(String accountName, String idVideo);
}
