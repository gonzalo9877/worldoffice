package com.worldoffice.productos.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.worldoffice.productos.dominio.Producto;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Value("${file.input}")
    private String fileInput;
    
    @Bean
    public FlatFileItemReader<Producto> reader() {
    	FlatFileItemReader<Producto> reader = new FlatFileItemReaderBuilder<Producto>().name("productItemReader")
        .resource(new ClassPathResource(fileInput))
        .delimited()
        .names(new String[] { "nombre", "marca", "precio", "stock", "estado", "descuento"})
        .fieldSetMapper(new BeanWrapperFieldSetMapper<Producto>() {{
            setTargetType(Producto.class);
        }})
        .build();
    	reader.setLinesToSkip(1);
        return reader;
    }
    
    @Bean
    public JdbcBatchItemWriter<Producto> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Producto>()
          .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
          .sql("INSERT INTO producto (nombre, marca, precio,stock,estado,descuento) VALUES (:nombre, :marca, :precio,:stock,:estado,:descuento)")
          .dataSource(dataSource)
          .build();
    }
    
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
          .incrementer(new RunIdIncrementer())
          .listener(listener)
          .flow(step1)
          .end()
          .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Producto> writer) {
    	Step step1 = stepBuilderFactory.get("step1")
        .<Producto, Producto> chunk(10)
        .reader(reader())
        .processor(processor())
        .writer(writer)
        .faultTolerant()
        .skip(Exception.class)
        .skipLimit(50)
        .build();
        return step1;
    }

    
    @Bean
    public ProductItemProcessor processor() {
        return new ProductItemProcessor();
    }
}
