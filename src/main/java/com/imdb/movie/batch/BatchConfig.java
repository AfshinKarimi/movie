package com.imdb.movie.batch;

import com.imdb.movie.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

import java.util.Map;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;
    private final ResourceLoader resourceLoader;

    private static final int MAX_ROWS_READ = 10000;

    private static final Map<Class<?>, String[]> FIELD_NAMES_MAP = Map.of(
            TitleBasics.class, new String[]{"tconst", "titleType", "primaryTitle", "originalTitle", "isAdult", "startYear", "endYear", "runtimeMinutes", "genres"},
            TitleRatings.class, new String[]{"tconst", "averageRating", "numVotes"},
            TitlePrincipals.class, new String[]{"tconst", "ordering", "nconst", "category", "job", "characters"},
            TitleCrew.class, new String[]{"tconst", "directors", "writers"},
            TitleEpisode.class, new String[]{"tconst", "parentTconst", "seasonNumber", "episodeNumber"},
            TitleAkas.class, new String[]{"titleId", "ordering", "title", "region", "language", "types", "attributes", "isOriginalTitle"},
            NameBasics.class, new String[]{"nconst", "primaryName", "birthYear", "deathYear", "primaryProfession", "knownForTitles"}
    );

    public <T> FlatFileItemReader<T> reader(Class<T> entityClass, String resourcePath) {
        FlatFileItemReader<T> reader = new FlatFileItemReader<>();
        Resource resource = resourceLoader.getResource(resourcePath);
        reader.setResource(resource);
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter("\t");
        tokenizer.setNames(getFieldNames(entityClass));

        CustomFieldSetMapper<T> fieldSetMapper = new CustomFieldSetMapper<>(entityClass);

        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        reader.setMaxItemCount(MAX_ROWS_READ);

        return reader;
    }


    public String[] getFieldNames(Class<?> entityClass) {
        return FIELD_NAMES_MAP.getOrDefault(entityClass, new String[]{});
    }

    public <T> JpaItemWriter<T> writer() {
        JpaItemWriter<T> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }


    public <T> Step step(String stepName, Class<T> entityClass, String resourcePath) {
        return new StepBuilder(stepName, jobRepository)
                .<T, T>chunk(1000, transactionManager)
                .reader(reader(entityClass, resourcePath))
                .writer(writer())
                .faultTolerant()
                .skipLimit(Integer.MAX_VALUE)
                .skip(Exception.class)
                .build();
    }


    public <T> Job importJob(String jobName, Class<T> entityClass, String resourcePath) {
        return new JobBuilder(jobName, jobRepository)
                .start(step(jobName + "Step", entityClass, resourcePath))
                .build();
    }


    @Bean
    public Job titleBasicsImportJob() {
        return importJob("titleBasicsImportJob", TitleBasics.class, "classpath:title.basics.tsv");
    }


    @Bean
    public Job titleRatingsImportJob() {
        return importJob("titleRatingsImportJob", TitleRatings.class, "classpath:title.ratings.tsv");
    }

    @Bean
    public Job titlePrincipalsImportJob() {
        return importJob("titlePrincipalsImportJob", TitlePrincipals.class, "classpath:title.principals.tsv");
    }

    @Bean
    public Job titleCrewImportJob() {
        return importJob("titleCrewImportJob", TitleCrew.class, "classpath:title.crew.tsv");
    }


    @Bean
    public Job titleEpisodeImportJob() {
        return importJob("titleEpisodeImportJob", TitleEpisode.class, "classpath:title.episode.tsv");
    }

    @Bean
    public Job titleAkasImportJob() {
        return importJob("titleAkasImportJob", TitleAkas.class, "classpath:title.akas.tsv");
    }

    @Bean
    public Job nameBasicsImportJob() {
        return importJob("nameBasicsImportJob", NameBasics.class, "classpath:name.basics.tsv");
    }

}