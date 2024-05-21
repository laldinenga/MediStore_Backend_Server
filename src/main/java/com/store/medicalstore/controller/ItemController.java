package com.medistore.MedServer.Controller;

import com.medistore.MedServer.Model.Item;
import com.medistore.MedServer.Service.Int_ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ItemController {
    private final Int_ItemService itemService;

    @PostMapping
    public Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItem(){
        return new ResponseEntity<>(itemService.getItems(), HttpStatus.FOUND);
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            itemService.saveImage(file);
            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

}
