package com.example.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.java.entity.KbDocument;

import java.util.List;

public interface KbDocumentService extends IService<KbDocument> {

    List<KbDocument> searchRelevant(String query, int limit);
}
