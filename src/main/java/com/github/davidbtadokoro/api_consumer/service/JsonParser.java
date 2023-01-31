package com.github.davidbtadokoro.api_consumer.service;

import java.util.List;

import com.github.davidbtadokoro.api_consumer.model.Content;

public interface JsonParser {

  public abstract List<? extends Content> parse();
  
}
