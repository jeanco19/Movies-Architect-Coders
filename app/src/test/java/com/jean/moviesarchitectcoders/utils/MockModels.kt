package com.jean.moviesarchitectcoders.utils

import com.jean.moviesarchitectcoders.domain.models.Movie

val expectedMovies = listOf(
    Movie(
        id = 933131,
        title = "Badland Hunters",
        overview = "After a deadly earthquake turns Seoul into a lawless badland, a fearless huntsman springs into action to rescue a teenager abducted by a mad doctor.",
        posterPath = "/zVMyvNowgbsBAL6O6esWfRpAcOb.jpg",
        releaseDate = "2024-01-26",
        voteAverage = 6.805
    ),
    Movie(
        id = 866398,
        title = "The Beekeeper",
        overview = "One man’s campaign for vengeance takes on national stakes after he is revealed to be a former operative of a powerful and clandestine organization known as Beekeepers.",
        posterPath = "/A7EByudX0eOzlkQ2FIbogzyazm2.jpg",
        releaseDate = "2024-01-10",
        voteAverage = 7.273
    )
)

val movie = Movie(
    id = 933131,
    title = "Badland Hunters",
    overview = "After a deadly earthquake turns Seoul into a lawless badland, a fearless huntsman springs into action to rescue a teenager abducted by a mad doctor.",
    posterPath = "/zVMyvNowgbsBAL6O6esWfRpAcOb.jpg",
    releaseDate = "2024-01-26",
    voteAverage = 6.805,
    isFavorite = true
)