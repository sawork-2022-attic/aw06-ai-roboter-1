package com.example.batch.service;

import com.example.batch.model.Product;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.util.List;

public class ProductWriter implements ItemWriter<Product>, StepExecutionListener {

    private static final String INSERT_PRODUCT = "insert into PRODUCT (main_cat,title,asin,category, imageURLHighRes, price) values (?,?,?,?,?,?)";


    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public void write(List<? extends Product> list) throws Exception {
//        list.stream().forEach(System.out::println);
        for (Product product: list) {
            try {
                jdbcTemplate.update(INSERT_PRODUCT, product.getMain_cat(), product.getTitle(), product.getAsin(),
                        String.join(",", product.getCategory()), String.join(",", product.getImageURLHighRes()),
                        Double.parseDouble(product.getPrice()));
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
            }

        }
        System.out.println("chunk written " + list.size());
    }
}
