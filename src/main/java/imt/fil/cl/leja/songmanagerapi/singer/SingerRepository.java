package imt.fil.cl.leja.songmanagerapi.singer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SingerRepository extends CrudRepository<Singer, Long> {
    @Query("SELECT s FROM Singer s JOIN FETCH s.songs song WHERE s.id = :singerId AND song.rating >= :minRating")
    Optional<Singer> findByIdWithBestSongs(@Param("singerId") Long singerId, @Param("minRating") int minRating);

}
