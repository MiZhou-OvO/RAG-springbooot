package com.example.java.controller;

import com.example.java.entity.KbDocument;
import com.example.java.pojo.Result;
import com.example.java.service.KbDocumentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    private final KbDocumentService kbDocumentService;

    public KnowledgeController(KbDocumentService kbDocumentService) {
        this.kbDocumentService = kbDocumentService;
    }

    @GetMapping("/list")
    public Result list() {
        List<KbDocument> list = kbDocumentService.list();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        KbDocument doc = kbDocumentService.getById(id);
        if (doc == null) {
            return Result.error("document not found");
        }
        return Result.success(doc);
    }

    @PostMapping
    public Result save(@RequestBody KbDocument document) {
        boolean saved = kbDocumentService.save(document);
        if (!saved) {
            return Result.error("save failed");
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody KbDocument document) {
        if (document.getId() == null) {
            return Result.error("id can not be null");
        }
        boolean updated = kbDocumentService.updateById(document);
        if (!updated) {
            return Result.error("update failed");
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean removed = kbDocumentService.removeById(id);
        if (!removed) {
            return Result.error("delete failed");
        }
        return Result.success();
    }
}
