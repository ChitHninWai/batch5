package com.example.bootcruds.controller;

import com.example.bootcruds.dao.AuthorDao;
import com.example.bootcruds.dao.BookDao;
import com.example.bootcruds.entity.Author;
import com.example.bootcruds.entity.Book;
import com.example.bootcruds.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
   private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/update/author")
    public String updateAuthor(@RequestParam("id")int id,Model model){
        model.addAttribute("author",
                bookService.findAuthorById(id));
        this.id=id;
        return "update-author";
    }
    @PostMapping("/save-update/v2")
    public String saveUpdateV2(@Valid Author author,BindingResult result){
        if(result.hasErrors()){
            return "update-author";
        }
        author.setId(id);
        bookService.updateAuthorV2(author);
        return "redirect:/authors";
    }
    int id;
    @PostMapping("/save-update")
    public String saveUpdateV1(@Valid Author author,BindingResult result){
        if(result.hasErrors()){
            return "update-author";
        }
        bookService.updateAuthorV1(id,author);
        return "redirect:/authors";
    }
    // /delete/author?id=1
    @GetMapping("/delete/author")
    public String removeAuthorById(@RequestParam("id")int id){
        bookService.removeAuthor(id);
        return "redirect:/authors";
    }

    @GetMapping("/author-form")
    public String authorForm(Model model){
        model.addAttribute("author",new Author());
        return "author-form";
    }
    @PostMapping("/author-form")
    public String saveAuthor(@Valid Author author, BindingResult result){
        if(result.hasErrors()){
            return "author-form";
        }
        bookService.saveAuthor(author);
        return "redirect:/authors";
    }
    @GetMapping("/authors")
    public String listAuthors(Model model){
        model.addAttribute("authors",bookService.listAuthors());
        return "authors";

    }
    @GetMapping({"/home"})
    public String home(){
        return "author-form";
    }
    @GetMapping("/book-form")
    public String bookForm(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("authors",bookService.listAuthors());
        return "pages/book-form";
    }
    @PostMapping("/save-book")
    public String saveBook(@Valid Book book,BindingResult result){
        if (result.hasErrors()){
            return "pages/book-form";
        }
      book.setAuthor(bookService.findAuthorById(book.getAuthor().getId()));
      bookService.saveBook(book);
      return "redirect:/books";
    }
    @GetMapping("/books")
    public String findAllBooks(Model model){

        model.addAttribute("books",bookService.findAllBooks());
        return "book-list";
    }
}
