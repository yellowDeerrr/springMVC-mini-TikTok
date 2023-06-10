package app.tiktok.repositores;

import app.tiktok.tables.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideosRepository extends JpaRepository<Videos, Long> {
    Videos findByNameAccountAndCodeVideo(String nameAccount, String codeVideo);
    List<Videos> findByNameVideo(String nameVideo);
}
