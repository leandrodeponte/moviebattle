package com.ada.challenge.moviebattle;

import com.ada.challenge.moviebattle.service.RetrieveMovieCatalogUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MoviebattleApplication {

	private final RetrieveMovieCatalogUseCase movieCatalog;

	public MoviebattleApplication(RetrieveMovieCatalogUseCase movieCatalog) {
		this.movieCatalog = movieCatalog;
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviebattleApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadMovieFromIMDB() {
		movieCatalog.execute(1L);
	}

}
