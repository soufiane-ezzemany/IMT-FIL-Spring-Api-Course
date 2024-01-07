package imt.fil.cl.leja.songmanagerapi.song;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    @Query("SELECT s FROM Song s WHERE s.id = :songId")
    Optional<Song>findSongById(@Param("songId") Long songId);

    @Query("SELECT s FROM Song s WHERE s.id IN :songIds")
    Optional<Set<Song>>findAllById(@Param("songIds") Set<Long> songIds);
}
