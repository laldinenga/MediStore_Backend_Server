package com.medistore.MedServer.Service;

import com.medistore.MedServer.Model.Item;
import com.medistore.MedServer.Repository.ItemRepository;
import com.medistore.MedServer.exception.ItemAlreadyExistException;
import com.medistore.MedServer.exception.ItemNotFoundexception;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ItemService implements Int_ItemService {
    private final ItemRepository itemRepository;
    @Override
    public Item addItem(Item item) {
        if (itemAlreadyExists(item.getName())){
            throw new ItemAlreadyExistException(item.getName() + "already exist");
        }
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item updateItem(Item item, Integer id) {
        return null;
    }

    @Override
    public Item getItemById(Integer id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundexception("sorry, item not found"));
    }

    @Override
    public void deleteItem(Integer id) {
        if (!itemRepository.existsById(id)){
            throw new ItemNotFoundexception("Sorry, Item no found");
        }
        itemRepository.deleteById(id);

    }

    private boolean itemAlreadyExists(String name) {
        return itemRepository.findByName(name).isPresent();
    }

    public void saveImage(MultipartFile file) throws IOException {
        Item item = new Item();
        item.setImageName(file.getOriginalFilename());
        item.setImageData(file.getBytes());
        itemRepository.save(item);
    }

}
