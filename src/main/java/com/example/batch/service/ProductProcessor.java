package com.example.batch.service;

import com.example.batch.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

import java.util.regex.Pattern;

public class ProductProcessor implements ItemProcessor<JsonNode, Product>, StepExecutionListener {

    private ObjectMapper objectMapper;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    public static boolean isNumeric(String str) {
        return str != null && NUMBER_PATTERN.matcher(str).matches();
    }

    @Override
    public Product process(JsonNode jsonNode) throws Exception {
        var product =  objectMapper.treeToValue(jsonNode, Product.class);
        product.setPrice(product.getPrice().replace("$", "").replace(" ", ""));
        if (product.getPrice().length() == 0 || !isNumeric(product.getPrice()))
            product.setPrice("0");

        return product;
    }
}
