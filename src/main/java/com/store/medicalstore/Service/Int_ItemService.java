package com.medistore.MedServer.Service;

import com.medistore.MedServer.Model.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Int_ItemService {
    Item addItem(Item item);            /*Add Item*/
    List<Item> getItems();             /* List Item*/
    Item updateItem(Item item, Integer id);      /*update item*/
    Item getItemById (Integer id);              /*get item*/

    void deleteItem(Integer id);      /*  delete item   */

    void saveImage(MultipartFile file) throws IOException;
}
