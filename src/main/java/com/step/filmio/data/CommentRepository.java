package com.step.filmio.data;

import com.step.filmio.models.Comment;
import com.step.filmio.models.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findAllByFilmId(Long filmId);
}
