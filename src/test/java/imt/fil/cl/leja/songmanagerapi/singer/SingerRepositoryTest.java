package imt.fil.cl.leja.songmanagerapi.singer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@DataJpaTest
@Sql("/data-singer-test.sql")
class SingerRepositoryTest {
    @Autowired
    private SingerRepository singerRepository;

    @AfterTestClass
    void tearDown() {
        singerRepository.deleteAll();
    }

    @Test
    void itShouldFindSingerByIdWithBestSongs() {
        // Assume there's a singer with ID 1 and a song with rating 5 in the test data
        Long singerId = 1L;
        Float minRating = 4f;

        Optional<Singer> optionalSinger = singerRepository.findByIdWithBestSongs(singerId, minRating);
        // Check if the user exists
        assertTrue(optionalSinger.isPresent());
        Singer singer = optionalSinger.get();
        assertEquals(singerId, singer.getId());

        // Assuming songs are eagerly fetched, you can access them directly
        assertTrue(singer.getSongs().stream().anyMatch(song -> song.getRating() >= minRating));
    }
}