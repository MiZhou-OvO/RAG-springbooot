package com.example.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java.entity.KbDocument;
import com.example.java.mapper.KbDocumentMapper;
import com.example.java.service.KbDocumentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KbDocumentServiceImpl extends ServiceImpl<KbDocumentMapper, KbDocument> implements KbDocumentService {

    @Override
    public List<KbDocument> searchRelevant(String query, int limit) {
        if (query == null || query.isBlank()) {
            return new ArrayList<>();
        }

        LambdaQueryWrapper<KbDocument> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(KbDocument::getContent, query)
                .or()
                .like(KbDocument::getTitle, query)
                .last("LIMIT " + limit);
        return list(wrapper);
    }
}
