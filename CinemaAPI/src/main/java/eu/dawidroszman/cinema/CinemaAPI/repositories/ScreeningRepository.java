package eu.dawidroszman.cinema.CinemaAPI.repositories;

import eu.dawidroszman.cinema.CinemaAPI.models.AuditoriumEntity;
import eu.dawidroszman.cinema.CinemaAPI.models.MovieEntity;
import eu.dawidroszman.cinema.CinemaAPI.models.ScreeningEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScreeningRepository extends Neo4jRepository<ScreeningEntity, UUID> {
    @Query("MATCH (m:Movie)<-[p:PLAYS]-(s:Screening)-[i:IS_IN]->(a:Auditorium) RETURN m,p,s,i,a")
    List<ScreeningEntity> findAll();

    ScreeningEntity findByDateAndAuditoriumAndMovie(LocalDateTime date, AuditoriumEntity auditorium, MovieEntity movie);

    Optional<ScreeningEntity> findById(UUID screeningId);

    Optional<List<ScreeningEntity>> findByDateAndAuditorium(LocalDateTime date, AuditoriumEntity auditorium);

    @Query("MATCH (m:Movie)<-[p:PLAYS]-(s:Screening)-[i:IS_IN]->(a:Auditorium) WHERE s.id = $id SET s.date = $date, s.time = $time, a.number = $auditoriumNumber")
    void modifyScreening(UUID id, LocalDateTime date, Integer auditoriumNumber, String title);
}
