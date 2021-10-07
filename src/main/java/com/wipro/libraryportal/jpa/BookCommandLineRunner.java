package com.wipro.libraryportal.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wipro.libraryportal.entity.Book;

@Component
public class BookCommandLineRunner implements CommandLineRunner {

    @Autowired
    private BookDao dao;

    @Override
    public void run(String... args) {
//         save a couple of books
        dao.save(new Book("9781906523374", "The Caine Prize for African Writing 2010: 11th Annual Collection", "New Internationalist", "English", 208));
        dao.save(new Book("9781558615007", "Women Writing Africa: West Africa and the Sahel", "Feminist Press at CUNY, The", "English", 560));
        dao.save(new Book("9782370610201", "Voyage à Marseille", "Editions Le Français C'est Facile", "French", 89));
        dao.save(new Book("9781571130976", "An Anthology of German Novellas", "Camden House", "German", 304));
        dao.save(new Book("9780143118336", "New Penguin Parallel Text", "Penguin Books; Bilingual edition", "English", 272));
        dao.save(new Book("9780253211101", "Oral Epics from Africa", "Indiana University Press", "English", 99));
    }

}

