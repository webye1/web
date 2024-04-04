package com.reins.bookstore.controller;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BookController
 * @Description TODO
 * @Author thunderBoy
 * @Date 2019/11/6 16:07
 */

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks(@RequestBody Map<String, String> params) {
        System.out.println("GET /getBooks");

        return bookService.getBooks();
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam(value = "id",required = false) Integer id){
        return bookService.findBookById(id);
    }
}
